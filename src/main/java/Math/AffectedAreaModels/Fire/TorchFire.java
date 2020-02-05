package Math.AffectedAreaModels.Fire;

import DataBase.Model.Department;
import DataBase.Model.Enterprise;
import DataBase.Model.Substance;
import Math.MatterAmountCalculation.Amount;

public class TorchFire implements BaseFireModel {

    @Override
    public double getThermalRadiationIntensty() {
        return 0;
    }

    @Override
    public void calculate(Substance substance, Amount amount, Department department, Enterprise enterprise) {

    }
}
