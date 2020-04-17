package Utils;

import lombok.Data;

@Data
public class CalculationVariableParameters {
    private double liquidTemperature;
    private double currentTemperature;
    private double fullnessPercent;
    private double windSpeed;
    private double airDensity;
    private double atmosphericPressure;
    private double equipmentPressure;
    private double holeHeight;
    private double holeDiameter;
    private double numberOfWorkers;
}
