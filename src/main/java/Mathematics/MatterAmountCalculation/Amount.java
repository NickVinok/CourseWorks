package Mathematics.MatterAmountCalculation;


import DataBase.Model.Department;
import DataBase.Model.Equipment;
import DataBase.Model.Substance;
import DataBase.Service.Coefficients;
import Mathematics.TmpClassForInputValues;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.stream.Stream;

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

    //TODO БРАТЬ НЕ ОТСЮДА, А ИЗ ПРИХОДЯЩЕГО ЗАПРОСА
    private double liquidTemperature;
    private double currentTemperature;
    private double windSpeed;
    private double airDensity;
    private double atmosphericPressure;
    private double equipmentPressure;

    private double holeHeight;
    private double holeDiameter;


    public void calculate(Equipment equipment, Department dep, Substance substance, TmpClassForInputValues input){
        this.radiusArray = generateRadiusArray();

        double massCoefficient = input.getFullnessPercent()*equipment.getVolume()*substance.getDensity();
        double quantityIB = this.quantityOfImmediatelyBoilingLiquidCalculation(massCoefficient, substance);
        double quantityLV = quantityOfLiquidInVaporFormCalculation(input.getFullnessPercent(), substance, equipment);
        double quantityLS = quantityOfLiquidSteamEvaporatingFromCane(substance, dep);
        double quantityML = quantityOfMirrorLiquidEvaporating(substance, massCoefficient, quantityIB);
        this.mass = quantityIB+quantityLV+quantityLS+quantityML;

        calculateProductConsumption(substance, equipment, input);
    }

    private double quantityOfImmediatelyBoilingLiquidCalculation(double massCoefficient, Substance substance){
        if(liquidTemperature<substance.getBoilingTemperature()){
            return 0;
        }
        double expVal = -1*(substance.getSpecificHeat()*(liquidTemperature-substance.getBoilingTemperature())
                /substance.getSpecificEvaporationHeat());
        return massCoefficient*(1-Math.pow(Math.E, expVal));
    }

    private double quantityOfLiquidInVaporFormCalculation(double fullnessPercent, Substance substance, Equipment equipment){
        double firstPart = (1-fullnessPercent)*substance.getMolarMass()/coefficients.getUniversalGasConst();
        double secondPart = equipmentPressure*equipment.getVolume()*(273+liquidTemperature); //TODO температура в оборудовании==температура жидкости в оборудовании
        return firstPart*secondPart;
    }

    private double quantityOfLiquidSteamEvaporatingFromCane(Substance substance, Department dep){
        if(liquidTemperature<substance.getBoilingTemperature()){
            return 0;
        }
        double heatTransitionCoefficient = Math.sqrt(substance.getSpecificEvaporationRate()
                *substance.getSpecificEvaporationHeat()*substance.getDensity());
        double firstPart = 2*(currentTemperature-substance.getBoilingTemperature())
                /substance.getSpecificEvaporationHeat();
        double secondPart =  heatTransitionCoefficient/Math.sqrt(Math.PI)*dep.getSquare()*Math.sqrt(time);
        return firstPart*secondPart;
    }

    private double quantityOfMirrorLiquidEvaporating(Substance substance, double massCoef, double immediateBoilingLiquidMass){
        double firstPartSteamPressure = (1/(substance.getBoilingTemperature()+273)-1/(273+currentTemperature));
        double secondPartSteamPressure = (substance.getSpecificEvaporationHeat()*substance.getMolarMass()
                /coefficients.getUniversalGasConst());
        double steamPressure = atmosphericPressure*Math.pow(Math.E, secondPartSteamPressure*firstPartSteamPressure);
        double evaporationIntensity = steamPressure*Math.sqrt(substance.getMolarMass())
                *coefficients.getAirSpeedAndTemperatureCoefficient()*Math.pow(10, -6);
        double evaporationArea = (massCoef-immediateBoilingLiquidMass)/substance.getDensity();
        return evaporationArea*evaporationIntensity*evaporationTime;
    }

    private void calculateProductConsumption(Substance substance, Equipment equipment, TmpClassForInputValues input){
        double geomPressure = equipment.getHeight()*input.getFullnessPercent()-this.holeHeight;
        double unitlessGeometricPressure = geomPressure + equipmentPressure/substance.getDensity() - atmosphericPressure/substance.getDensity();
        this.productConsumption = coefficients.getFlowRateCoefficient()*(holeDiameter*1000)
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
