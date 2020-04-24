package com.diploma.Diploma.Mathematics.RiskCalculationModule;

import com.diploma.Diploma.DataBase.Model.Territory;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RiskCalculation {
    private ArrayList<ArrayList<Double>> potentialRiskForEmergencySubtypes = new ArrayList<>();
    private ArrayList<Double> potentialRisk = new ArrayList<>();
    private ArrayList<Double> avgIndividualRisk = new ArrayList<>();
    private Float collectiveRisk = 0f;
    

    private ArrayList<Double> calculatePotentialRiskForEmergencySubType(double potentiallyDangerousSituationProbability, double emergencyScenarioProbability,
                               List<Double> exposureProbability){
        ArrayList<Double> results = new ArrayList<>();
        for(int i = 0; i<exposureProbability.size();i++){
            double result = potentiallyDangerousSituationProbability*emergencyScenarioProbability*exposureProbability.get(i);
            results.add(result);
        }
        return results;
    }

    public void calculate(double potentiallyDangerousSituationProbability, List<Double> emergencyScenarioProbability,
                          List<List<Double>> exposuresProbabilities, List<Double> radiuses, List<Territory> territories,
                          double enterpriseArea, double numberOfWorkers){
        for(int i = 0; i<exposuresProbabilities.size();i++){
            ArrayList<Double> potentialRiskForEmergencySubtype = this.calculatePotentialRiskForEmergencySubType(potentiallyDangerousSituationProbability,
                                                                    emergencyScenarioProbability.get(i), exposuresProbabilities.get(i));
            potentialRiskForEmergencySubtypes.add(potentialRiskForEmergencySubtype);
        }
        for(int i = 0; i<radiuses.size();i++){
            double potentialRiskForRadius = 0d;
            for(int j = 0; j<this.potentialRiskForEmergencySubtypes.size();j++){
                potentialRiskForRadius += this.potentialRiskForEmergencySubtypes.get(j).get(i);
            }
            potentialRisk.add(potentialRiskForRadius);
        }
        getZones(radiuses, territories, enterpriseArea, numberOfWorkers);

    }

    private void getZones(List<Double> radiuses, List<Territory> territories, double enterpriseArea, double numberOfWorkers){
        ArrayList<Double> numberOfPeopleInArea = new ArrayList<>();
        double tmp = enterpriseArea;
        numberOfPeopleInArea.add(tmp*0.000001*numberOfWorkers);
        for(Territory t: territories){
            tmp = t.getDistanceFromPreviousTerritory()*t.getDistanceFromPreviousTerritory()*Math.PI - tmp;
            numberOfPeopleInArea.add(tmp*0.000001*t.getPopulationDensity());
        }

        int radiusIndex;
        double enterpriseIndividualRisk = 0;
        for(radiusIndex = 0; radiusIndex<radiuses.size();radiusIndex++){
            if(radiuses.get(radiusIndex)>Math.sqrt(enterpriseArea/Math.PI)){
                break;
            }
            enterpriseIndividualRisk+=potentialRisk.get(radiusIndex);
        }
        double enterpriseAvgIndividualRisk = enterpriseIndividualRisk/numberOfPeopleInArea.get(0);
        this.avgIndividualRisk.add(enterpriseAvgIndividualRisk);

        int s = 1;
        for(Territory territory: territories){
            double individualRiskSum = 0;
            for(int i = radiusIndex; i<radiuses.size();i++){
                if(radiuses.get(i)>territory.getDistanceFromPreviousTerritory())
                {
                    break;
                }
               individualRiskSum += potentialRisk.get(i);
            }
            double avgRisk = individualRiskSum/numberOfPeopleInArea.get(s);
            this.avgIndividualRisk.add(avgRisk);
            s++;
        }
    }
}
