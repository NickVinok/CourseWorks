package Mathematics;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@Data
public class RiskCalculation {
    private ArrayList<ArrayList<Float>> territoryRisk = new ArrayList<>();
    private ArrayList<ArrayList<Float>> individualRisk = new ArrayList<>();
    private Float avgIndividualRisk = 0f;
    private Float collectiveRisk = 0f;
    
    //Второй аргумент - услованя вероятность реализации механизма воздействия(второй массив) при реализации аварии в точке (X,Y)(3 и 4 массивы)
    public void calculateRisks(ArrayList<Float> accidentProbability, ArrayList<ArrayList<ArrayList<ArrayList<Float>>>> effectProbability){
        //генерация точек расчёта
    }

}
