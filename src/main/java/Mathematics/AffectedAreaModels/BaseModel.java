package Mathematics.AffectedAreaModels;

import DataBase.Model.Department;
import DataBase.Model.Enterprise;
import DataBase.Model.Substance;
import Mathematics.CalculationRequest;
import Mathematics.MatterAmountCalculation.Amount;

import java.util.ArrayList;

public interface BaseModel {
    void calculate(Substance substance, Amount amount, Department department, Enterprise enterprise, CalculationRequest calculationRequest);
    ArrayList<Double> getProbitFunctionValues(ArrayList<Double> radiusArray);
}
