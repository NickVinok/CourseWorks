package com.diploma.Diploma.Mathematics.AffectedAreaModels.Fire;

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
public class StraightFire implements BaseFireModel {
    private ArrayList<Double> thermalRadiationIntensity;
    private ArrayList<Double> probitFunctionValue;
    private ArrayList<Double> irradianceAngleCoefficients;
    private ArrayList<Double> expositionTime;
    private String type;

    private Coefficients coefficients;

    @Override
    public ArrayList<Double> getThermalRadiationIntensty() {
        return this.thermalRadiationIntensity;
    }

    @Override
    public void calculate(Substance substance, Amount amount, Department department, Coefficients coefficients,
                          Enterprise enterprise, CalculationVariableParameters calculationVariableParameters, CloudCombustionModeRepo ccmr) {
        //Его можно взять из документа, приведённого ниже
        //Но есть упрощённая версия
        //Она считается на этапе определения количества участвующего в аварии вещества

        //Здесь используется понятие площади пролива
        //Его мы берём из документа TODO РД 03-26-2007
        this.coefficients = coefficients;
        double airDensity = calculationVariableParameters.getAtmosphericPressure() * 29 /
                (calculationVariableParameters.getCurrentTemperature() * coefficients.getUniversalGasConst());

        //Для простоты будем считать, что площадь пролива равна площади помещения, в котором произошёл пролив
        double straitPane= department.getArea();

        double effectiveDiameter = Math.sqrt(4 * straitPane / Math.PI);

        //Удельная массовая скорость выгорания
        //TODO Мб возможно брать из базы
        double specificMassBurnoutRate = 0.001 * substance.getSpecificBurnoutRate()
                / (substance.getSpecificEvaporationRate() / 1000 + substance.getSpecificHeat() //из-за кДж в формуле
                * (substance.getBoilingTemperature() - calculationVariableParameters.getCurrentTemperature()));

        //Расчёт длины пламени
        double flameLength;
        double coefficient = calculationVariableParameters.getWindSpeed()
                / Math.cbrt(coefficients.getFreeFallAcceleration() * effectiveDiameter * specificMassBurnoutRate
                / substance.getFuelVapourDensity());
        double massBurnoutPart = specificMassBurnoutRate
                / (airDensity * Math.sqrt(coefficients.getFreeFallAcceleration() * effectiveDiameter));
        if (coefficient >= 1) {
            flameLength = 55 * effectiveDiameter * Math.pow(coefficient, 0.21) * Math.pow(massBurnoutPart, 0.67);
        } else {
            flameLength = 42 * Math.pow(massBurnoutPart, 0.61) * effectiveDiameter;
        }
        double flameAngleCos;
        if (coefficient < 1) {
            flameAngleCos = 1;
        } else {
            flameAngleCos = Math.pow(coefficient, -0.5);
        }

        //расчёт среднеповерхностной тепловой интенсивности излучения
        double meanThermalRadiationIntensity;
        if (substance.getType().equals("нефть и нефтепродукты")) {
            double e = Math.pow(Math.E, (-0.12) * effectiveDiameter);
            meanThermalRadiationIntensity = 140 * e + 20 * (1 - e);
        } else if (substance.getType().equals("однокомпонентная жидкость")) {
            meanThermalRadiationIntensity = 0.4 * specificMassBurnoutRate / (1 + 4 * flameLength / effectiveDiameter);
        } else {
            meanThermalRadiationIntensity = 1;
        }


        irradianceAngleCoefficientCalculation(effectiveDiameter, flameLength, flameAngleCos, amount.getRadiusArray());
        ArrayList<Double> atmosphericTransmissionCoefficient = new ArrayList<>();
        for (Double dist : amount.getRadiusArray()) {
            double coef = Math.pow(Math.E, -7.0 / 10000 * (dist - 0.5 * effectiveDiameter));
            atmosphericTransmissionCoefficient.add(coef);
        }
        this.thermalRadiationIntensity=new ArrayList<>();
        for (int i = 0; i < atmosphericTransmissionCoefficient.size(); i++) {
            /*System.out.println(atmosphericTransmissionCoefficient);
            System.out.println(irradianceAngleCoefficients);
            System.out.println(meanThermalRadiationIntensity);*/
            double value = atmosphericTransmissionCoefficient.get(i) * irradianceAngleCoefficients.get(i) * meanThermalRadiationIntensity;
            //System.out.println(value);
            thermalRadiationIntensity.add(value);
        }
    }

    @Override
    public ArrayList<Double> getProbitFunctionValues(ArrayList<Double> radiusArray) {
        //Предположим, что говоря под зоной непосредственного воздействия пламени пожара
        //подразумевается попапдание человека в зону эффективного диаметра пролива d
        //Пока на это забиваем

        //Находим с какого радиуса интенивность теплового излучения меньше 4 кВт
        int indexOfSafeRadius = 0;
        for (int i = 0; i < thermalRadiationIntensity.size(); i++) {
            if (thermalRadiationIntensity.get(i) <= 4) {
                indexOfSafeRadius = i;
                break;
            }
        }

        //Считаем расстояние до этого радиуса
        this.expositionTime = new ArrayList<>();
        for (Double radius : radiusArray) {
            double time = 0;
            double tmp = radiusArray.get(indexOfSafeRadius)- radius;
            if (tmp > 0) {
                time = coefficients.getHumanFireDetectionTime() + tmp / coefficients.getHumanSpeedProceedingToSafeZone();
            } else {
                time = coefficients.getHumanFireDetectionTime();
            }
            expositionTime.add(time);
        }

        probitFunctionValue = new ArrayList<>();
        for (int i = 0; i < this.thermalRadiationIntensity.size(); i++) {
            double value = -12.8 + 2.56 * Math.log(expositionTime.get(i) * Math.pow(thermalRadiationIntensity.get(i), 1.33));
            probitFunctionValue.add(value);
        }
        return probitFunctionValue;
    }

    //Расчёт углового коэффициента облучённости
    private void irradianceAngleCoefficientCalculation(double effectiveDiameter, double flameLength, double flameAngleCos,
                                                       ArrayList<Double> distanceFromCenter) {
            this.irradianceAngleCoefficients = new ArrayList<>();
            double a = 2 * flameLength / effectiveDiameter;
            double angle = Math.acos(flameAngleCos);
            //System.out.println(flameLength);
            System.out.println(effectiveDiameter);
            //System.out.println(a);
            //System.out.println(angle);
            for (Double dist : distanceFromCenter) {
                double b = 2 * dist / effectiveDiameter;
                double A = Math.sqrt(a * a + (b + 1) * (b + 1) - 2 * a * (b + 1) * Math.sin(angle));
                double B = Math.sqrt(a * a + (b - 1) * (b - 1) - 2 * a * (b - 1) * Math.sin(angle));
                double C = Math.sqrt(1 + (b * b - 1) * Math.cos(angle));
                double D = Math.sqrt((b - 1) / (b + 1));
                double E = a * flameAngleCos / (b - a * Math.sin(angle));
                double F = Math.sqrt(b * b - 1);
                double G = Math.atan((a * b - F * F * Math.sin(angle)) / (F * C)) + Math.atan(F * Math.sin(angle) / C);
                double verticalIrradiance = 1 / Math.PI *
                        (-1 * E * Math.atan(D)
                                + E * ((a * a + (b + 1) * (b + 1) - 2 * b * (1 + a + Math.sin(angle))) / (A * B)) * Math.atan(A * D / B)
                                + (flameAngleCos / C) * G);
                double horizontalIrradiance = 1 / Math.PI *
                        ((Math.atan(1 / D) + (Math.sin(angle) / C) * G)
                                - ((a * a + (b + 1) * (b + 1) - 2 * (b + 1 + a * b * Math.sin(angle))) / (A * B)) * Math.atan(A * D / B));
                irradianceAngleCoefficients.add(
                        Math.sqrt(verticalIrradiance * verticalIrradiance + horizontalIrradiance * horizontalIrradiance)
                );
            }
    }


    public StraightFire(){
        this.type="Пожар";
    }
}
