package com.diploma.Diploma.Mathematics.AffectedAreaModels.Explosion;

import com.diploma.Diploma.DataBase.Model.*;
import com.diploma.Diploma.DataBase.Model.Keys.CloudCombustionModeKey;
import com.diploma.Diploma.DataBase.Repo.CloudCombustionModeRepo;
import com.diploma.Diploma.DataBase.Service.Coefficients;
import com.diploma.Diploma.Utils.CalculationVariableParameters;
import com.diploma.Diploma.Mathematics.MatterAmountCalculation.Amount;
import lombok.Data;

import java.util.ArrayList;

@Data
public class VaporExplosion implements BaseExplosionModel {
    private ArrayList<Double> excessPressure;
    private ArrayList<Double> impulse;
    private ArrayList<Double> probitFunctionValue;
    private String type;

    private Coefficients coefficients;

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
    public void calculate(Substance substance, Amount amount, Department department, Coefficients coefficients,
                          Enterprise enterprise, CalculationVariableParameters calculationVariableParameters, CloudCombustionModeRepo ccmr) {
        this.cloudCombustionModeRepo = ccmr;
        //Считаем эффективный энергозапас горючей смеси
        double E = amount.getVapourMass() //масса горючего вещества, участвующего в облаке
                *coefficients.getSubstanceParticipationInExplosion() //коэффициент участия гор.вещ. во взрыве
                *substance.getSpecificBurnoutRate()*1000 //удельная теплота сгорания парогазовой среды в Дж/кг
                *2; //Тупо из примера) Хотим, чтобы было в Дж, поэтому умнржаем на тысячу

        //TODO брать значение из пришедшего запроса/базы
        double C0 = coefficients.getSpeedOfSound();
        double Vr = 0; //Скорость фронта пламени

        CloudCombustionModeKey key = new CloudCombustionModeKey(department.getClutterClass().getId(),
                substance.getExplosionSensitivity().getId());

        CloudCombustionMode ccm = cloudCombustionModeRepo.findById(key).get();

        if(ccm.getFlameFrontSpeed()==0){
            Vr=Math.pow(ccm.getK()*amount.getVapourMass(), 1/6f);
        } else {
            Vr=ccm.getFlameFrontSpeed();
        }
        //две временные константы для проверки
        E = 1332858d*1000000;
        Vr = 300d;
        double sigma = coefficients.getCombustionProductExpansionDegree();

        ArrayList<Double> unitlessDistance = new ArrayList<>();
        //В случае дефлагарации пылевоздушного облака величина эффективного энергозапаса умножается на коэффициент sigma
        if(ccm.getClassificationNumber()!=1){
            E*=(sigma-1)/sigma;
        }
        for(Double dist: amount.getRadiusArray()){
            double uDist = dist/
                    (Math.pow(E/calculationVariableParameters.getAtmosphericPressure(), 1/3f));
            if(uDist<0.34){
                unitlessDistance.add(0.34);
            } else{
                unitlessDistance.add(uDist);
            }
        }

        this.excessPressure = new ArrayList<>();
        this.impulse = new ArrayList<>();
        for(Double dist : unitlessDistance){
            double unitlessPressure;
            double unitlessImpulse;
            if(ccm.getClassificationNumber()==1) {
                unitlessPressure = Math.pow(Math.E,
                        -1.124-1.66*(Math.log(dist)+0.26*Math.pow(Math.log(dist), 2))
                        );
                unitlessImpulse = Math.pow(Math.E,
                        -3.412 - 0.898*(Math.log(dist)-0.0096*Math.pow(Math.log(dist), 2))
                        );
            } else{
                unitlessPressure =
                        Math.pow(Vr / C0, 2)
                                * ((sigma - 1) / sigma)
                                * (0.83 / dist - 0.14 / Math.pow(dist, 2));
                double tmp = Vr / C0 * (sigma - 1) / sigma;
                unitlessImpulse =
                                tmp
                                * (1 - 0.4 * tmp)
                                * (0.06 / dist + 0.01 / Math.pow(dist, 2) - 0.0025 / Math.pow(dist, 3));
            }
            double pressure = unitlessPressure * calculationVariableParameters.getAtmosphericPressure();
            double unitImpulse = unitlessImpulse * Math.pow(calculationVariableParameters.getAtmosphericPressure(), 2/3f) * Math.pow(E, 1/3f) / C0;
            this.excessPressure.add(pressure);
            this.impulse.add(unitImpulse);
        }
    }

    @Override
    public ArrayList<Double> getProbitFunctionValues(ArrayList<Double> radiusArray) {
        probitFunctionValue = new ArrayList<>();

        for(int i =0; i<impulse.size();i++){
            double V = Math.pow(17500/(excessPressure.get(i)/1000), 8.4) + Math.pow(290/impulse.get(i), 9.3);
            double value = 5 - 0.26*Math.log(V);
            probitFunctionValue.add(value);
        }

        return  probitFunctionValue;
    }

    public VaporExplosion(){
        this.type="Взрыв";
    }
}
