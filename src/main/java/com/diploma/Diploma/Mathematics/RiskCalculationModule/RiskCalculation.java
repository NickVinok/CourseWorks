package com.diploma.Diploma.Mathematics.RiskCalculationModule;

import com.diploma.Diploma.DataBase.Model.RiskInTerritoryCalculation;
import com.diploma.Diploma.DataBase.Model.Territory;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class RiskCalculation {
    private ArrayList<ArrayList<Double>> potentialRiskForEmergencySubtypes = new ArrayList<>();
    private ArrayList<Double> potentialRisk = new ArrayList<>();
    private ArrayList<RiskInTerritoryCalculation> avgIndividualRisk = new ArrayList<>();
    private double collectiveRisk = 0f;

    private ArrayList<Double> calculatePotentialRiskForEmergencySubType(double potentiallyDangerousSituationProbability, double emergencyScenarioProbability,
                                                                        List<Double> exposureProbability) {
        ArrayList<Double> results = new ArrayList<>();
        for (int i = 0; i < exposureProbability.size(); i++) {
            double result = potentiallyDangerousSituationProbability * emergencyScenarioProbability * exposureProbability.get(i) / 100;
            results.add(result);
        }
        return results;
    }

    public void calculate(double potentiallyDangerousSituationProbability, List<Double> emergencyScenarioProbability,
                          List<List<Double>> exposuresProbabilities, List<Double> radiuses, List<Territory> territories,
                          double enterpriseArea, int numberOfWorkers) {
        for (int i = 0; i < exposuresProbabilities.size(); i++) {
            ArrayList<Double> potentialRiskForEmergencySubtype = this.calculatePotentialRiskForEmergencySubType(potentiallyDangerousSituationProbability,
                    emergencyScenarioProbability.get(i), exposuresProbabilities.get(i));
            potentialRiskForEmergencySubtypes.add(potentialRiskForEmergencySubtype);
        }
        for (int i = 0; i < radiuses.size(); i++) {
            double potentialRiskForRadius = 0d;
            for (int j = 0; j < this.potentialRiskForEmergencySubtypes.size(); j++) {
                potentialRiskForRadius += this.potentialRiskForEmergencySubtypes.get(j).get(i);
            }
            potentialRisk.add(potentialRiskForRadius);
        }
        getZones(radiuses, territories, enterpriseArea, numberOfWorkers);
    }

    private void getZones(List<Double> radiuses, List<Territory> territories, double enterpriseArea, int numberOfWorkers) {
        ArrayList<Double> numberOfPeopleInArea = new ArrayList<>();
        double tmp = enterpriseArea;
        numberOfPeopleInArea.add(tmp * 0.000001 * numberOfWorkers);
        for (Territory t : territories) {
            tmp = t.getTerritoryRadius() * t.getTerritoryRadius() * Math.PI - tmp;
            numberOfPeopleInArea.add(tmp * 0.000001 * t.getPopulationDensity());
        }
        //сортируем территории по порядку
        territories = territories.stream()
                .sorted((t1, t2) -> Integer.compare(t1.getOrder(), t2.getOrder()))
                .collect(Collectors.toList());

        ArrayList<ArrayList<Double>> listOfTerritoryRadiuses = new ArrayList<>();
        ArrayList<ArrayList<Double>> listOfTerritoryRisks = new ArrayList<>();
        //растасовываем радиусы и риски по территориям
        int initialRadiusIndex = 0;
        for (Territory t : territories) {
            ArrayList<Double> territoryRadiuses = new ArrayList<>();
            ArrayList<Double> territoryRisks = new ArrayList<>();
            for (int i = initialRadiusIndex; i < radiuses.size(); i++) {
                if (radiuses.get(i) > t.getTerritoryRadius() || i == radiuses.size() - 1) {
                    initialRadiusIndex = i;
                    listOfTerritoryRadiuses.add(territoryRadiuses);
                    listOfTerritoryRisks.add(territoryRisks);
                    break;
                }
                territoryRadiuses.add(radiuses.get(i));
                territoryRisks.add(potentialRisk.get(i));
            }
        }
        //Считаем средний индивидуальный риск
        double previousArea = 0;
        for (int i = 0; i < listOfTerritoryRadiuses.size(); i++) {
            double value = calculateAvgIndividualRiskForTerritory(territories.get(i), listOfTerritoryRadiuses.get(i),
                    listOfTerritoryRisks.get(i), numberOfWorkers, previousArea);
            RiskInTerritoryCalculation rtc = new RiskInTerritoryCalculation();
            rtc.setTerritory(territories.get(i));
            rtc.setAverageRisk(value);
            avgIndividualRisk.add(rtc);
            previousArea = territories.get(i).getTerritoryRadius();
        }
    }

    private Double calculateAvgIndividualRiskForTerritory(Territory territory, List<Double> radiuses,
                                                          List<Double> potentialRisks, int numberOfWorkers, double previousTerritoryArea) {
        double avgIndividualRiskForTerritory = 0d;
        if (territory.getOrder() == 0) {
            for (int i = 0; i < radiuses.size(); i++) {
                avgIndividualRiskForTerritory += potentialRisks.get(i) * territory.getZone().getProbabilityOfPresenceInZone();
            }
            collectiveRisk+=avgIndividualRiskForTerritory*numberOfWorkers;
            avgIndividualRiskForTerritory /= radiuses.size();

            return avgIndividualRiskForTerritory;
        } else {
            int numberOfPeopleInArea = 0;
            for (int i = 0; i < radiuses.size(); i++) {
                double area = 0;
                double tmp = radiuses.get(i) * radiuses.get(i) * Math.PI;
                area = tmp - previousTerritoryArea;
                previousTerritoryArea = tmp;

                int numberOfPeopleInRadius = (int) (area*territory.getPopulationDensity()/1000000);
                collectiveRisk += potentialRisks.get(i)* numberOfPeopleInRadius;
                avgIndividualRiskForTerritory += potentialRisks.get(i)* numberOfPeopleInRadius
                        * territory.getZone().getProbabilityOfPresenceInZone();
                numberOfPeopleInArea+=numberOfPeopleInRadius;
            }
            avgIndividualRiskForTerritory/=numberOfPeopleInArea;

            return avgIndividualRiskForTerritory;
        }
    }

}
