package Mathematics.MatterAmountCalculation;

import DataBase.Model.Equipment;
import DataBase.Model.Substance;
import Mathematics.TmpClassForInputValues;
import lombok.Data;

@Data
public class Amount {
    private double mass;
    private double liquidTemperature;
    private double volume;
    private double productConsumption;

    //TODO БРАТЬ НЕ ОТСЮДА, А ИЗ ПРИХОДЯЩЕГО ЗАПРОСА
    private double currentTemperature;
    private double windSpeed;
    private double airDensity;

    public void calculate(Equipment equipment, Substance substance, TmpClassForInputValues input){
        double massCoefficient = input.getFullnessPercent()*equipment.getVolume()*substance.getDensity();

    }

    private double quantityOfImmediatelyBoilingLiquidCalculation(double massCoefficient){

    }

    private double quantityOfLiquidInForOfVaporCalculation(double fullnessPercent){

    }

    private double quantityOfLiquidSteamEvaporatingFromCane(Substance substance){

    }

    private double quantityOfMirrorLiquidEvaporating(){

    }
}
