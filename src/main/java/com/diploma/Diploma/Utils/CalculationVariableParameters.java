package com.diploma.Diploma.Utils;

import lombok.Data;

@Data
public class CalculationVariableParameters {
    private double liquidTemperature;
    private double currentTemperature;
    private double windSpeed;
    private double atmosphericPressure;
    private double equipmentPressure;
    private double holeHeight;
    private double holeDiameter;
    private int numberOfWorkers;
}
