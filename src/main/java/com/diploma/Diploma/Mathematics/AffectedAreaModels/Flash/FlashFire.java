package com.diploma.Diploma.Mathematics.AffectedAreaModels.Flash;

import com.diploma.Diploma.DataBase.Model.Department;
import com.diploma.Diploma.DataBase.Model.Enterprise;
import com.diploma.Diploma.DataBase.Model.Substance;
import com.diploma.Diploma.Utils.CalculationVariableParameters;
import com.diploma.Diploma.Mathematics.MatterAmountCalculation.Amount;
import lombok.Data;

import java.util.ArrayList;

@Data
public class FlashFire implements BaseFlashModel {
    private ArrayList<Double> probitFunctionValue;
    private double radius;
    private String type;

    @Override
    public void calculate(Substance substance, Amount amount, Department department, Enterprise enterprise, CalculationVariableParameters calculationVariableParameters) {
        //По-хорошему нужно поделить все вещества на горючие газы и легко воспламеняющиеся жидкости
        //Но так как это дополнительная морока и мы не работаем с гг, то оставляем формулу только под лвж

        double rNKPR = 7.8 * Math.pow(amount.getMass()/(substance.getFuelVapourDensity()*substance.getConcentrationLimitMinimalValue()),0.33);
        this.radius = 1.2 * rNKPR;
    }

    @Override
    public ArrayList<Double> getProbitFunctionValues(ArrayList<Double> radiusArray) {
        probitFunctionValue = new ArrayList<>();
        for(Double radius: radiusArray){
            if(radius<=this.radius){
                probitFunctionValue.add(1d);
            }
            else {
                probitFunctionValue.add(0d);
            }
        }

        return probitFunctionValue;
    }

    public FlashFire(){
        this.type = "Вспышка";
    }
}
