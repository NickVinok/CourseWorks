package Mathematics.AffectedAreaModels;

import DataBase.Model.Department;
import DataBase.Model.Enterprise;
import DataBase.Model.Substance;
import Mathematics.MatterAmountCalculation.Amount;

import java.util.ArrayList;

public interface BaseModel {
    void calculate(Substance substance, Amount amount, Department department, Enterprise  enterprise);
    ArrayList<Double> getProbitFunctionValues();
}
