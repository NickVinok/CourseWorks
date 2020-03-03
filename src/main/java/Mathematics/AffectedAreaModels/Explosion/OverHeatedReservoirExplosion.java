package Mathematics.AffectedAreaModels.Explosion;

import DataBase.Model.Department;
import DataBase.Model.Enterprise;
import DataBase.Model.Substance;
import DataBase.Service.Coefficients;
import Mathematics.MatterAmountCalculation.Amount;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Data
public class OverHeatedReservoirExplosion implements BaseExplosionModel {
    private ArrayList<Double> excessPressure;
    private ArrayList<Double> impulse;
    private ArrayList<Double> probitFunctionValue;
    @Autowired
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
    public void calculate(Substance substance, Amount amount, Department department, Enterprise enterprise) {

        double explosionEfficientEnergy=coefficients.getPressureWaveEnergy()*substance.getSpecificHeat()*amount.getMass()
                *(amount.getLiquidTemperature()-substance.getBoilingTemperature());
                //Если есть предохранительное устройство, то температуру жижкости мы расчитываем по другой формуле
        double P0 = 101458; //Атмосферное давление
        ArrayList<Integer> distanceFromCenter = new ArrayList<>(List.of(1,2,3,4,5,10,15,20,30,40,50));
        double mpr = (explosionEfficientEnergy/4.52)/1000000;

        for(Integer dist: distanceFromCenter){
            double pressure = P0*(0.8*Math.pow(mpr,0.33)/dist
                    + 3*Math.pow(mpr,0.66)/Math.pow(dist,2)
                    + 5*mpr/Math.pow(dist,3));
            double imp = 123*Math.pow(mpr,0.66)/dist;
            excessPressure.add(pressure);
            impulse.add(imp);
        }
    }

    @Override
    public ArrayList<Double> getProbitFunctionValues(ArrayList<Double> radiusArray) {
        return null;
    }
}
