package com.diploma.Diploma.Logics;

import lombok.Data;

import java.util.HashMap;

@Data
public class CalculationResults {
    private int radius;
    private HashMap<String, Double> exposureTypeValueMap;
    private double probitFunctionValue;
    private double probability;
    private double potentialRisk;
}
