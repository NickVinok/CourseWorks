package Math.MatterAmountCalculation;

import lombok.Data;

@Data

public class Amount {
    private double mass;
    private double liquidTemperature;
    private double volume;
    //TODO БРАТЬ НЕ ОТСЮДА, А ИЗ ПРИХОДЯЩЕГО ЗАПРОСА
    private double currentTemperature;
    private double windSpeed;
    private double airDensity;

    public double getMass(){
        return this.mass;
    }
}
