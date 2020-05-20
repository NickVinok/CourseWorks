package com.diploma.Diploma.Mathematics.AffectedAreaModels.Explosion;

import com.diploma.Diploma.DataBase.Model.Department;
import com.diploma.Diploma.DataBase.Model.Enterprise;
import com.diploma.Diploma.DataBase.Model.Substance;
import com.diploma.Diploma.DataBase.Repo.CloudCombustionModeRepo;
import com.diploma.Diploma.DataBase.Service.Coefficients;
import com.diploma.Diploma.Utils.CalculationVariableParameters;
import com.diploma.Diploma.Mathematics.MatterAmountCalculation.Amount;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@Data
public class OverHeatedReservoirExplosion implements BaseExplosionModel {
    private ArrayList<Double> excessPressure;
    private ArrayList<Double> impulse;
    private ArrayList<Double> probitFunctionValue;
    private String type;

    private Coefficients coefficients;

    @Override
    public ArrayList<Double> getExcessPressure() {
        return this.excessPressure;
    }

    @Override
    public ArrayList<Double> getImpulse() {
        return this.impulse;
    }

    @Override
    public void calculate(Substance substance, Amount amount, Department department, Coefficients coefficients,
                          Enterprise enterprise, CalculationVariableParameters calculationVariableParameters, CloudCombustionModeRepo ccmr) {

        double explosionEfficientEnergy=coefficients.getPressureWaveEnergy()*substance.getSpecificHeat()*amount.getMass()
                *(calculationVariableParameters.getLiquidTemperature()-substance.getBoilingTemperature());
                //Если есть предохранительное устройство, то температуру жижкости мы расчитываем по другой формуле
        double mpr = (explosionEfficientEnergy/4.52)/1000000;

        for(Double dist: amount.getRadiusArray()){
            double pressure = calculationVariableParameters.getAtmosphericPressure()*(0.8*Math.pow(mpr,0.33)/dist
                    + 3*Math.pow(mpr,0.66)/Math.pow(dist,2)
                    + 5*mpr/Math.pow(dist,3));
            double imp = 123*Math.pow(mpr,0.66)/dist;
            excessPressure.add(pressure);
            impulse.add(imp);
        }
    }

    @Override
    public ArrayList<Double> getProbitFunctionValues(ArrayList<Double> radiusArray) {
        probitFunctionValue = new ArrayList<>();

        for(int i =0; i<impulse.size();i++){
            double V = Math.pow(17500/excessPressure.get(i), 8.4) + Math.pow(290/impulse.get(i), 9.3);
            double value = 5 - 0.26*Math.log(V);
            probitFunctionValue.add(value);
        }

        return  probitFunctionValue;
    }

    public OverHeatedReservoirExplosion(){
        this.type="Взрыв";
    }
}
