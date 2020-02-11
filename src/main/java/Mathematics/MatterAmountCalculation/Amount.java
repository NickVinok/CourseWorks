package Mathematics.MatterAmountCalculation;


import DataBase.Model.Department;
import DataBase.Model.Equipment;
import DataBase.Model.Substance;
import DataBase.Service.Coefficients;
import Mathematics.TmpClassForInputValues;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class Amount {
    @Autowired
    private Coefficients coefficients;

    private double time; //Время контакта жидкости с твёрдой поверхностью (оно константно или задаётся пользователем, или расчитывается)
    private double mass;
    private double volume;
    private double productConsumption;

    //TODO БРАТЬ НЕ ОТСЮДА, А ИЗ ПРИХОДЯЩЕГО ЗАПРОСА
    private double liquidTemperature;
    private double currentTemperature;
    private double windSpeed;
    private double airDensity;
    private double atmosphericPressure;
    //TODO спросить, что вообще нужно класть в базу, касательно оборудования
    private double equipmentPressure;

    public void calculate(Equipment equipment, Department dep, Substance substance, TmpClassForInputValues input){
        double massCoefficient = input.getFullnessPercent()*equipment.getVolume()*substance.getDensity();
        double quantityIB = this.quantityOfImmediatelyBoilingLiquidCalculation(massCoefficient, substance);
        double quantityLV = quantityOfLiquidInVaporFormCalculation(input.getFullnessPercent(), substance, equipment);
        double quantityLS = quantityOfLiquidSteamEvaporatingFromCane(substance, dep);
        double quantityML = quantityOfMirrorLiquidEvaporating(substance);
        this.mass = quantityIB+quantityLV+quantityLS+quantityML;
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

    private double quantityOfMirrorLiquidEvaporating(Substance substance){
        double firstPartSteamPressure = (1/(substance.getBoilingTemperature()+273)-1/(273+currentTemperature));
        double secondPartSteamPressure = (substance.getSpecificEvaporationHeat()*substance.getMolarMass()
                /coefficients.getUniversalGasConst());
        double steamPressure = atmosphericPressure*Math.pow(Math.E, secondPartSteamPressure*firstPartSteamPressure);
        double evaporationRate = steamPressure*Math.sqrt(substance.getMolarMass())
                *coefficients.getAirSpeedAndTemperatureCoefficient();
        return evaporationRate;
    }
}
