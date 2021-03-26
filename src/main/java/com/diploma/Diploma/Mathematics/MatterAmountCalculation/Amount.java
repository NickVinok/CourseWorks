package com.diploma.Diploma.Mathematics.MatterAmountCalculation;


import com.diploma.Diploma.DataBase.Model.Department;
import com.diploma.Diploma.DataBase.Model.EquipmentClass;
import com.diploma.Diploma.DataBase.Model.Substance;
import com.diploma.Diploma.DataBase.Service.Coefficients;
import com.diploma.Diploma.Utils.CalculationVariableParameters;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Data
@Service
public class Amount {
    @Autowired
    private Coefficients coefficients;

    private double vapourMass;
    private double quantityOfLiquidEscapingReservoir;
    private double volume;
    private double productConsumption;
    private double evaporationTime=3600; //Ебануть в константы потом мб
    private ArrayList<Double> radiusArray;


    public void calculate(EquipmentClass equipmentClass, Department dep,Substance underlyingSurface, Substance substance,
                          double fullnessPercent, CalculationVariableParameters input){
        this.radiusArray = generateRadiusArray();

        double massCoefficient = fullnessPercent* equipmentClass.getVolume()*substance.getDensity(); //Обманчивое название, в методе - совершенно другое значение
        double quantityIB = quantityOfImmediatelyBoilingLiquidCalculation(massCoefficient, substance, input);
        double quantityLV = quantityOfLiquidInVaporFormCalculation(fullnessPercent, substance, equipmentClass, input);
        double quantityLS = quantityOfLiquidSteamEvaporatingFromCane(underlyingSurface, substance,massCoefficient-quantityIB, dep, input);
        double quantityML = quantityOfMirrorLiquidEvaporating(substance, underlyingSurface, input, equipmentClass, fullnessPercent, dep);
        /*System.out.println(quantityIB);
        System.out.println(quantityLV);
        System.out.println(quantityLS);
        System.out.println(quantityML);*/
        this.vapourMass = quantityIB+quantityLV+quantityLS+quantityML;
        this.quantityOfLiquidEscapingReservoir = massCoefficient;
        //System.out.println(this.vapourMass);

        calculateProductConsumption(substance, equipmentClass,fullnessPercent, input);
    }

    private double quantityOfImmediatelyBoilingLiquidCalculation(double massCoefficient, Substance substance, CalculationVariableParameters input){
        if(input.getLiquidTemperature()<substance.getBoilingTemperature()){
            return 0;
        }
        double expVal = -1*(substance.getSpecificHeat()*(input.getLiquidTemperature()-substance.getBoilingTemperature())
                /substance.getSpecificEvaporationHeat());

        return massCoefficient*(1-Math.pow(Math.E, expVal));
    }

    private double quantityOfLiquidInVaporFormCalculation(double fullnessPercent, Substance substance, EquipmentClass equipmentClass, CalculationVariableParameters input){
        double firstPart = (1-fullnessPercent)*substance.getMolarMass()/coefficients.getUniversalGasConst();
        double secondPart = input.getEquipmentPressure()* equipmentClass.getVolume()/(273+input.getLiquidTemperature()); //TODO температура в оборудовании==температура жидкости в оборудовании
        return firstPart*secondPart;
    }

    private double quantityOfLiquidSteamEvaporatingFromCane(Substance underlyingSurface, Substance substance, double subtraction,
                                                            Department dep, CalculationVariableParameters input){
        if(input.getLiquidTemperature()<substance.getBoilingTemperature()){
            return 0;
        }
        double heatTransitionCoefficient = Math.sqrt(underlyingSurface.getHeatTransitionCoefficient()
                *underlyingSurface.getSpecificHeat()*underlyingSurface.getDensity());
        double firstPart = 2*(input.getCurrentTemperature()-substance.getBoilingTemperature())
                /substance.getSpecificEvaporationHeat();
        double spillArea = underlyingSurface.getUnderlyingSurfaceCoefficient()*subtraction/substance.getDensity();
        if(dep.getArea()<spillArea || dep.getArea()==0){
            spillArea = dep.getArea();
        }
        double secondPart =  heatTransitionCoefficient/Math.sqrt(Math.PI)*spillArea*Math.sqrt(evaporationTime);
        return firstPart*secondPart;
    }

    private double quantityOfMirrorLiquidEvaporating(Substance substance, Substance underlyingSurface, CalculationVariableParameters input,
                                                     EquipmentClass eqclass, double fullnessPercent, Department department){
        double firstPartSteamPressure = (1/(substance.getBoilingTemperature()+273)-1/(273+input.getLiquidTemperature()));
        double secondPartSteamPressure = (substance.getSpecificEvaporationHeat()*substance.getMolarMass()
                /coefficients.getUniversalGasConst());
        double steamPressure = input.getAtmosphericPressure()*Math.pow(Math.E, secondPartSteamPressure*firstPartSteamPressure);
        double evaporationIntensity = (steamPressure/1000)*Math.sqrt(substance.getMolarMass())
                *coefficients.getAirSpeedAndTemperatureCoefficient()*Math.pow(10, -6);
        //допускаем, что объём жидкости, поступившей в пространство равен объёму аппарата, на котором прошла разгерметизация
        double floorCoefficient = underlyingSurface.getUnderlyingSurfaceCoefficient();
        double evaporationArea = eqclass.getVolume()*fullnessPercent*floorCoefficient;
        if(evaporationArea>department.getArea()) evaporationArea=department.getArea();

        return evaporationArea*evaporationIntensity*evaporationTime;
    }

    private void calculateProductConsumption(Substance substance, EquipmentClass equipmentClass,double fullnessPercent,  CalculationVariableParameters input){
        double geomPressure = equipmentClass.getHeight()*fullnessPercent-input.getHoleHeight();
        double unitlessGeometricPressure = geomPressure + input.getEquipmentPressure()/substance.getDensity()
                - input.getAtmosphericPressure()/substance.getDensity();
        this.productConsumption = coefficients.getFlowRateCoefficient()*(input.getHoleDiameter()*1000)
                *Math.sqrt(2*coefficients.getFreeFallAcceleration()*unitlessGeometricPressure);
    }

    private ArrayList<Double> generateRadiusArray(){
        ArrayList<Double> tmp = new ArrayList<>();
        for(double d = 10; d<2000; d+=10){
            tmp.add(d);
        }
        return tmp;
    }
    public void nullify(){
        this.radiusArray = null;
        this.vapourMass = 0;
        this.productConsumption = 0;
        this.volume = 0;
    }
}
