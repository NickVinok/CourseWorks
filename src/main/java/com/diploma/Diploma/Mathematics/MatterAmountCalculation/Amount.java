package com.diploma.Diploma.Mathematics.MatterAmountCalculation;


import com.diploma.Diploma.DataBase.Model.Department;
import com.diploma.Diploma.DataBase.Model.EquipmentClass;
import com.diploma.Diploma.DataBase.Model.Substance;
import com.diploma.Diploma.DataBase.Service.Coefficients;
import com.diploma.Diploma.Utils.CalculationVariableParameters;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@Data
public class Amount {
    @Autowired
    private Coefficients coefficients;

    private double time; //Время контакта жидкости с твёрдой поверхностью (оно константно или задаётся пользователем, или расчитывается)
    private double mass;
    private double volume;
    private double productConsumption;
    private double evaporationTime=3600; //Ебануть в константы потом мб
    private ArrayList<Double> radiusArray;


    public void calculate(EquipmentClass equipmentClass, Department dep, Substance substance,
                          double fullnessPercent, CalculationVariableParameters input){
        this.radiusArray = generateRadiusArray();

        double massCoefficient = fullnessPercent* equipmentClass.getVolume()*substance.getDensity();
        double quantityIB = this.quantityOfImmediatelyBoilingLiquidCalculation(massCoefficient, substance, input);
        double quantityLV = quantityOfLiquidInVaporFormCalculation(fullnessPercent, substance, equipmentClass, input);
        double quantityLS = quantityOfLiquidSteamEvaporatingFromCane(substance, dep, input);
        double quantityML = quantityOfMirrorLiquidEvaporating(substance, massCoefficient, quantityIB, input);
        this.mass = quantityIB+quantityLV+quantityLS+quantityML;

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
        double secondPart = input.getEquipmentPressure()* equipmentClass.getVolume()*(273+input.getLiquidTemperature()); //TODO температура в оборудовании==температура жидкости в оборудовании
        return firstPart*secondPart;
    }

    private double quantityOfLiquidSteamEvaporatingFromCane(Substance substance, Department dep, CalculationVariableParameters input){
        if(input.getLiquidTemperature()<substance.getBoilingTemperature()){
            return 0;
        }
        double heatTransitionCoefficient = Math.sqrt(substance.getSpecificEvaporationRate()
                *substance.getSpecificEvaporationHeat()*substance.getDensity());
        double firstPart = 2*(input.getCurrentTemperature()-substance.getBoilingTemperature())
                /substance.getSpecificEvaporationHeat();
        double secondPart =  heatTransitionCoefficient/Math.sqrt(Math.PI)*dep.getArea()*Math.sqrt(time);
        return firstPart*secondPart;
    }

    private double quantityOfMirrorLiquidEvaporating(Substance substance, double massCoef, double immediateBoilingLiquidMass, CalculationVariableParameters input){
        double firstPartSteamPressure = (1/(substance.getBoilingTemperature()+273)-1/(273+input.getLiquidTemperature()));
        double secondPartSteamPressure = (substance.getSpecificEvaporationHeat()*substance.getMolarMass()
                /coefficients.getUniversalGasConst());
        double steamPressure = input.getAtmosphericPressure()*Math.pow(Math.E, secondPartSteamPressure*firstPartSteamPressure);
        double evaporationIntensity = steamPressure*Math.sqrt(substance.getMolarMass())
                *coefficients.getAirSpeedAndTemperatureCoefficient()*Math.pow(10, -6);
        double evaporationArea = (massCoef-immediateBoilingLiquidMass)/substance.getDensity();
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
        for(double d = 0; d<2000; d++){
            tmp.add(d);
        }
        return tmp;
    }
}
