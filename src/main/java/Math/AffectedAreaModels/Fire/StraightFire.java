package Math.AffectedAreaModels.Fire;

import DataBase.Model.Department;
import DataBase.Model.Enterprise;
import DataBase.Model.Substance;
import DataBase.Service.Coefficients;
import Math.MatterAmountCalculation.Amount;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@Data
public class StraightFire implements BaseFireModel {
    private ArrayList<Double> thermalRadiationIntensity;
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

        double meanThermalRadiationIntensity;
        if(substance.getType().equals("нефть и нефтепродукты")){
            double e=Math.pow(Math.E, (-0.12)*effectiveDiameter);
            meanThermalRadiationIntensity=140*e+20*(1-e);
        } else if(substance.getType().equals("однокомпонентная жидкость")){
            double specificBurnoutRate = 0.001*substance.getSpecificBurnoutRate()
                    /(substance.getSpecificEvaporationRate()+substance.getSpecificHeat()
                        *(substance.getBoilingTemperature()-amount.getCurrentTemperature()));
        }
    }
}
