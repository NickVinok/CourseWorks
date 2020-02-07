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
public class StraightFire implements BaseFireModel {
    private ArrayList<Double> thermalRadiationIntensity;

    private ArrayList<Double> irradianceAngleCoefficients;
    @Autowired
    private Coefficients coefficients;

    @Override
    public ArrayList<Double> getThermalRadiationIntensty() {
        return this.thermalRadiationIntensity;
    }

    @Override
    public void calculate(Substance substance, Amount amount, Department department, Enterprise enterprise) {
        //Его можно взять из документа, приведённого ниже
        //Но есть упрощённая версия
        //Она считается на этапе определения количества участвующего в аварии вещества

        //Здесь используется понятие площади пролива
        //Его мы берём из документа TODO РД 03-26-2007

        //Для вычисления эффективного диаметра пролива
        double straitPane;
        if (enterprise.getCavingArea()!=0){
            //TODO брать коэффициент, в зависимости от типа поверхности, на которую идёт пролив
            straitPane=amount.getVolume()*coefficients.getStraitConcreteCoefficient();
        } else{
            straitPane=enterprise.getCavingArea();
        }
        double effectiveDiameter=Math.sqrt(4*straitPane/Math.PI);

        //Удельная массовая скорость выгорания
        //TODO Мб возможно брать из базы
        double specificMassBurnoutRate = 0.001*substance.getSpecificBurnoutRate()
                /(substance.getSpecificEvaporationRate()+substance.getSpecificHeat()
                *(substance.getBoilingTemperature()-amount.getCurrentTemperature()));

        //Расчёт длины пламени
        double flameLength;
        double coefficient = amount.getWindSpeed()
                /Math.cbrt(coefficients.getFreeFallAcceleration() *effectiveDiameter*specificMassBurnoutRate
                    /substance.getFuelVapourDensity());
        double massBurnoutPart = specificMassBurnoutRate
                /(amount.getAirDensity() *Math.sqrt(coefficients.getFreeFallAcceleration()*effectiveDiameter));
        if(coefficient>=1){
            flameLength=55*effectiveDiameter*Math.pow(coefficient, 0.21)*Math.pow(massBurnoutPart,0.67);
        } else {
            flameLength=42*Math.pow(massBurnoutPart,0.61)*effectiveDiameter;
        }
        double flameAngleCos;
        if(coefficient<1){
            flameAngleCos=1;
        } else{
            flameAngleCos=Math.pow(coefficient,-0.5);
        }

        //расчёт среднеповерхностной тепловой интенсивности излучения
        double meanThermalRadiationIntensity;
        if(substance.getType().equals("нефть и нефтепродукты")){
            double e=Math.pow(Math.E, (-0.12)*effectiveDiameter);
            meanThermalRadiationIntensity=140*e+20*(1-e);
        } else if(substance.getType().equals("однокомпонентная жидкость")){
            meanThermalRadiationIntensity=0.4*specificMassBurnoutRate/(1+4*flameLength/effectiveDiameter);
        } else{
            meanThermalRadiationIntensity = 1;
        }

        ArrayList<Integer> distanceFromCenter = new ArrayList<>(List.of(1,2,3,4,5,10,15,20,30,40,50));
        irradianceAngleCoefficientCalculation(effectiveDiameter,flameLength,flameAngleCos, distanceFromCenter);
        ArrayList<Double> atmosphericTransmissionCoefficient = new ArrayList<>();
        for(Integer dist: distanceFromCenter){
            double coef = Math.pow(Math.E, -7.0/10000*(dist-0.5*effectiveDiameter));
            atmosphericTransmissionCoefficient.add(coef);
        }
        for(int i = 0; i<atmosphericTransmissionCoefficient.size();i++){
            thermalRadiationIntensity.add(atmosphericTransmissionCoefficient.get(i)*irradianceAngleCoefficients.get(i)*meanThermalRadiationIntensity);
        }
    }
    //Расчёт углового коэффициента облучённости
    private void irradianceAngleCoefficientCalculation(double effectiveDiameter, double flameLength, double flameAngleCos,
                                                       ArrayList<Integer> distanceFromCenter){
        this.irradianceAngleCoefficients = new ArrayList<>();
        double a = 2*flameLength/effectiveDiameter;
        double angle = Math.acos(flameAngleCos);
        for(Integer dist: distanceFromCenter){
            double b = 2*dist/effectiveDiameter;
            double A = Math.sqrt(a*a+(b+1)*(b+1) - 2*a*(b+1)*Math.sin(angle));
            double B = Math.sqrt(a*a+(b-1)*(b-1) - 2*a*(b-1)*Math.sin(angle));
            double C = Math.sqrt(1+(b*b-1)*Math.cos(angle));
            double D = Math.sqrt((b-1)/(b+1));
            double E = a*flameAngleCos/(b-a*Math.sin(angle));
            double F = Math.sqrt(b*b-1);
            double G = Math.atan((a*b-F*F*Math.sin(angle))/(F*C))+Math.atan(F*Math.sin(angle)/C);
            double verticalIrradiance = 1/Math.PI *
                    (-1*E*Math.atan(D)
                            +E*((a*a+(b+1)*(b+1)-2*b*(1+a+Math.sin(angle)))/(A*B)) * Math.atan(A*D/B)
                    +(flameAngleCos/C) * G);
            double horizontalIrradiance = 1/Math.PI*
                    ((Math.atan(1/D) + (Math.sin(angle)/C)*G)
                    -((a*a+(b+1)*(b+1) - 2*(b+1+a*b*Math.sin(angle))) / (A*B))*Math.atan(A*D/B));
            irradianceAngleCoefficients.add(
                    Math.sqrt(verticalIrradiance*verticalIrradiance+horizontalIrradiance*horizontalIrradiance)
            );
        }
    }
}
