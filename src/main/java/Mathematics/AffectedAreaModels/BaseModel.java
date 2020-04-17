package Mathematics.AffectedAreaModels;

import DataBase.Model.Department;
import DataBase.Model.Enterprise;
import DataBase.Model.Substance;
import Utils.CalculationVariableParameters;
import Mathematics.MatterAmountCalculation.Amount;

import java.util.ArrayList;

public interface BaseModel {
    String getType();
    void calculate(Substance substance, Amount amount, Department department, Enterprise enterprise, CalculationVariableParameters calculationVariableParameters);
    ArrayList<Double> getProbitFunctionValues(ArrayList<Double> radiusArray);
}
