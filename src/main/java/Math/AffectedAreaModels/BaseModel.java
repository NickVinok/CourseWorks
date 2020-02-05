package Math.AffectedAreaModels;

import DataBase.Model.Department;
import DataBase.Model.Enterprise;
import DataBase.Model.Substance;
import Math.MatterAmountCalculation.Amount;

public interface BaseModel {
    void calculate(Substance substance, Amount amount, Department department, Enterprise  enterprise);
}
