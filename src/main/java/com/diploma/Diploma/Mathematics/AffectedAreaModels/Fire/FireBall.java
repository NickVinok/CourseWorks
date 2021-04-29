package com.diploma.Diploma.Mathematics.AffectedAreaModels.Fire;

import com.diploma.Diploma.DataBase.Model.Department;
import com.diploma.Diploma.DataBase.Model.Enterprise;
import com.diploma.Diploma.DataBase.Model.Substance;
import com.diploma.Diploma.DataBase.Repo.CloudCombustionModeRepo;
import com.diploma.Diploma.DataBase.Service.Coefficients;
import com.diploma.Diploma.Utils.CalculationVariableParameters;
import com.diploma.Diploma.Mathematics.MatterAmountCalculation.Amount;
import lombok.Data;

import java.util.ArrayList;

@Data
public class FireBall implements BaseFireModel {
    private ArrayList<Double> thermalRadiationIntensity;
    private ArrayList<Double> probitFunctionValue;
    private double fireballExistenceTime;
    private double expositionTime;
    private String type;

    private Coefficients coefficients;

    @Override
    public ArrayList<Double> getThermalRadiationIntensty() {
        return this.thermalRadiationIntensity;
    }

    @Override
    public void calculate(Substance substance, Amount amount, Department department, Coefficients coefficients,
                          Enterprise enterprise, CalculationVariableParameters calculationVariableParameters, CloudCombustionModeRepo ccmr) {
        double fireballEffectiveDiameter = 6.48 * Math.pow(amount.getQuantityOfLiquidEscapingReservoir(), 0.325);
        double H = fireballEffectiveDiameter;
        this.thermalRadiationIntensity = new ArrayList<>();
        for(Double r: amount.getRadiusArray()){
            double root = Math.pow(r, 2) + Math.pow(H, 2);

            double irradianceAngleCoefficient = Math.pow(fireballEffectiveDiameter, 2) / (4 * root);

            double atmosphericTransmissionCoefficient = Math.pow(Math.E, (-7*Math.pow(10, -4)
            * (Math.sqrt(root)-fireballEffectiveDiameter/2)));
            double intensity = irradianceAngleCoefficient*atmosphericTransmissionCoefficient*
                    coefficients.getMeanThermalRadiationIntensity();
            thermalRadiationIntensity.add(intensity);
        }
        expositionTime =  0.92 * Math.pow(amount.getQuantityOfLiquidEscapingReservoir(), 0.303);
        fireballExistenceTime = 0.852*Math.pow(amount.getQuantityOfLiquidEscapingReservoir(),0.26);
    }

    @Override
    public ArrayList<Double> getProbitFunctionValues(ArrayList<Double> radiusArray) {
       probitFunctionValue = new ArrayList<>();
       for(Double intensity: this.thermalRadiationIntensity){
            double value = -12.8 + 2.56*Math.log(expositionTime*Math.pow(intensity, 4/3));
            probitFunctionValue.add(value);
       }
       return probitFunctionValue;
    }

    public FireBall(){
        this.type="Пожар";
    }
}
