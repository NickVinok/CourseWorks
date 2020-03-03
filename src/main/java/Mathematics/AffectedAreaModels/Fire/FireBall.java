package Mathematics.AffectedAreaModels.Fire;

import DataBase.Model.Department;
import DataBase.Model.Enterprise;
import DataBase.Model.Substance;
import DataBase.Service.Coefficients;
import Mathematics.CalculationRequest;
import Mathematics.MatterAmountCalculation.Amount;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@Data
public class FireBall implements BaseFireModel {
    private ArrayList<Double> thermalRadiationIntensity;
    private ArrayList<Double> probitFunctionValue;
    private double fireballExistenceTime;
    private double expositionTime;

    @Autowired
    private Coefficients coefficients;

    @Override
    public ArrayList<Double> getThermalRadiationIntensty() {
        return this.thermalRadiationIntensity;
    }

    @Override
    public void calculate(Substance substance, Amount amount, Department department, Enterprise enterprise, CalculationRequest calculationRequest) {
        double fireballEffectiveDiameter = 6.48 * Math.pow(amount.getMass(), 0.325);
        double H = fireballEffectiveDiameter;

        for(Double r: amount.getRadiusArray()){
            double root = Math.pow(r, 2) + Math.pow(H, 2);

            double irradianceAngleCoefficient = Math.pow(fireballEffectiveDiameter, 2)
                    /
                    4*(root);
            double atmosphericTransmissionCoefficient = Math.exp((-7.0) / 10000
            * (Math.sqrt(root)-fireballEffectiveDiameter/2));

            thermalRadiationIntensity.add(irradianceAngleCoefficient*atmosphericTransmissionCoefficient*coefficients.getMeanThermalRadiationIntensity());
        }
        expositionTime =  0.92 * Math.pow(amount.getMass(), 0.303);
        fireballExistenceTime = 0.852*Math.pow(amount.getMass(),0.26);
    }

    @Override
    public ArrayList<Double> getProbitFunctionValues(ArrayList<Double> radiusArray) {
       probitFunctionValue = new ArrayList<>();
       for(Double intensity: this.thermalRadiationIntensity){
            double value = -12.8 + 2.56*Math.log(expositionTime*Math.pow(intensity, 1.33));
            probitFunctionValue.add(value);
       }
       return probitFunctionValue;
    }
}
