package com.diploma.Diploma.Mathematics.AffectedAreaModels;

import com.diploma.Diploma.DataBase.Model.Department;
import com.diploma.Diploma.DataBase.Model.Enterprise;
import com.diploma.Diploma.DataBase.Model.Substance;
import com.diploma.Diploma.DataBase.Repo.CloudCombustionModeRepo;
import com.diploma.Diploma.DataBase.Service.Coefficients;
import com.diploma.Diploma.Utils.CalculationVariableParameters;
import com.diploma.Diploma.Mathematics.MatterAmountCalculation.Amount;

import java.util.ArrayList;

public interface BaseModel {
    String getType();
    void calculate(Substance substance, Amount amount, Department department, Coefficients coefficients,
                   Enterprise enterprise, CalculationVariableParameters calculationVariableParameters, CloudCombustionModeRepo ccmr);
    ArrayList<Double> getProbitFunctionValues(ArrayList<Double> radiusArray);
}
