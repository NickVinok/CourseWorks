package Math.AffectedAreaModels.Fire;

import DataBase.Model.Department;
import DataBase.Model.Enterprise;
import DataBase.Model.Substance;
import DataBase.Service.Coefficients;
import Math.MatterAmountCalculation.Amount;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Data
public class FireBall implements BaseFireModel {
    private ArrayList<Double> thermalRadiationIntensity;
    private double fireballExistenceTime;

    @Autowired
    private Coefficients coefficients;

    @Override
    public ArrayList<Double> getThermalRadiationIntensty() {
        return this.thermalRadiationIntensity;
    }

    @Override
    public void calculate(Substance substance, Amount amount, Department department, Enterprise enterprise) {
        double fireballEffectiveDiameter = 6.48 * Math.pow(amount.getMass(), 0.325);
        double H =fireballEffectiveDiameter;

        ArrayList<Integer> distanceFromCenter = new ArrayList<>(List.of(1,2,3,4,5,10,15,20,30,40,50));

        for(Integer r: distanceFromCenter){
            double root = Math.pow(r, 2) + Math.pow(H, 2);

            double irradianceAngleCoefficient = Math.pow(fireballEffectiveDiameter, 2)
                    /
                    4*(root);
            double atmosphericTransmissionCoefficient = Math.exp((-7.0) / 10000
            * (Math.sqrt(root)-fireballEffectiveDiameter/2));

            thermalRadiationIntensity.add(irradianceAngleCoefficient*atmosphericTransmissionCoefficient*coefficients.getMeanThermalRadiationIntensity());
        }

        fireballExistenceTime = 0.852*Math.pow(amount.getMass(),0.26);
    }
}
