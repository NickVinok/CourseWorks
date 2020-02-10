package Mathematics.AffectedAreaModels.Explosion;

import DataBase.Model.*;
import DataBase.Repo.CloudCombustionModeRepo;
import DataBase.Service.Coefficients;
import Mathematics.MatterAmountCalculation.Amount;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class VaporExplosion implements BaseExplosionModel {
    private ArrayList<Double> excessPressure;
    private ArrayList<Double> impulse;

    @Autowired
    private Coefficients coefficients;
    @Autowired
    private CloudCombustionModeRepo cloudCombustionModeRepo;

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
        //Считаем эффективный энергозапас горючей смеси
        double E = amount.getMass() //масса горючего вещества, участвующего в облаке
                *coefficients.getSubstanceParticipationInExplosion() //коэффициент участия гор.вещ. во взрыве
                *substance.getSpecificHeat(); //удельная теплота сгорания парогазовой среды

        //TODO брать значение из пришедшего запроса/базы
        double P0 = 101458; //Атмосферное давление
        double C0 = coefficients.getSpeedOfSound();
        double Vr = 0; //Скорость фронта пламени

        CloudCombustionModeKey key = new CloudCombustionModeKey(department.getClutterClass().getId(),
                substance.getExplosionSensitivityClass().getId());
        CloudCombustionMode ccm = cloudCombustionModeRepo.finByCloudCombustionKey(key);

        if(ccm.getFlameFrontSpeed()==0){
            Vr=Math.pow(ccm.getK()*amount.getMass(), 1/6f);
        } else {
            Vr=ccm.getFlameFrontSpeed();
        }

        ArrayList<Integer> distanceFromCenter = new ArrayList<>(List.of(1,2,3,4,5,10,15,20,30,40,50));
        ArrayList<Double> unitlessDistance = new ArrayList<>();
        
        for(Integer dist: distanceFromCenter){
            unitlessDistance.add(dist/
                    (Math.pow(E/P0, 0.33)));
        }

        for(Double dist : unitlessDistance){
            double unitlessPressure = Math.pow(Vr/C0,2)
                    *((coefficients.getCombustionProductExpansionDegree()-1)/coefficients.getCombustionProductExpansionDegree()) //TODO ВОЗМОЖНО СТОИТ ЗАНЕСТИ В ОТДУЛЬНУЮ ПЕРЕМЕННУЮ
                    *(0.83/dist-0.14/Math.pow(dist,2));
            double unitlessImpulse = Vr/C0*((coefficients.getCombustionProductExpansionDegree()-1)/coefficients.getCombustionProductExpansionDegree())
                    *(1-0.4*(coefficients.getCombustionProductExpansionDegree()-1)*Vr/(coefficients.getCombustionProductExpansionDegree()*C0))
                    *(0.06/dist + 0.01/Math.pow(dist,2) + 0.0025/Math.pow(dist,3));

            double pressure=unitlessPressure*P0;
            double unitImpulse=unitlessImpulse*Math.pow(P0,2/3f)*Math.pow(E,1/3f)/C0;

            this.excessPressure.add(pressure);
            this.impulse.add(unitImpulse);
        }
    }
}
