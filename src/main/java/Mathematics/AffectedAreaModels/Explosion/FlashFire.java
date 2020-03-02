package Mathematics.AffectedAreaModels.Explosion;

import DataBase.Model.Department;
import DataBase.Model.Enterprise;
import DataBase.Model.Substance;
import Mathematics.MatterAmountCalculation.Amount;
import lombok.Data;

import java.util.ArrayList;

@Data
public class FlashFire implements BaseExplosionModel {
    private ArrayList<Double> probitFunctionValue;
    private double rNKPR;

    @Override
    public ArrayList<Double> getExcessPressure() {
        return null;
    }

    @Override
    public ArrayList<Double> getImpulse() {
        return null;
    }

    @Override
    public void calculate(Substance substance, Amount amount, Department department, Enterprise enterprise) {
        //По-хорошему нужно поделить все вещества на горючие газы и легко воспламеняющиеся жидкости
        //Но так как это дополнительная морока и мы не работаем с гг, то оставляем формулу только под лвж

        //Как я понял масса в amount - масса паров вещества, так что спокойно используем её
        this.rNKPR = 7.8 * Math.pow(amount.getMass()/(substance.getFuelVapourDensity()*substance.getConcentrationLimitMinimalValue()),0.33);
    }

    @Override
    public ArrayList<Double> getProbitFunctionValues() {
        return null;
    }
}
