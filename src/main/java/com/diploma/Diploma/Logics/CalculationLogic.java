package com.diploma.Diploma.Logics;

import com.diploma.Diploma.Controllers.RequsetObjects.CalculationRequest.CalculationStartRequest;
import com.diploma.Diploma.DataBase.Model.*;
import com.diploma.Diploma.DataBase.Model.Keys.DamagingExposureCalculationKey;
import com.diploma.Diploma.DataBase.Repo.*;
import com.diploma.Diploma.DataBase.Service.EmergencyService;
import com.diploma.Diploma.Mathematics.AffectedAreaModels.BaseModel;
import com.diploma.Diploma.Mathematics.AffectedAreaModels.Explosion.BaseExplosionModel;
import com.diploma.Diploma.Mathematics.AffectedAreaModels.Explosion.OverHeatedReservoirExplosion;
import com.diploma.Diploma.Mathematics.AffectedAreaModels.Explosion.VaporExplosion;
import com.diploma.Diploma.Mathematics.AffectedAreaModels.Fire.BaseFireModel;
import com.diploma.Diploma.Mathematics.AffectedAreaModels.Fire.FireBall;
import com.diploma.Diploma.Mathematics.AffectedAreaModels.Fire.StraightFire;
import com.diploma.Diploma.Mathematics.AffectedAreaModels.Fire.TorchFire;
import com.diploma.Diploma.Mathematics.AffectedAreaModels.Flash.FlashFire;
import com.diploma.Diploma.Mathematics.MatterAmountCalculation.Amount;
import com.diploma.Diploma.Mathematics.RiskCalculationModule.ProbitFunctionsToExposureProbability;
import com.diploma.Diploma.Mathematics.RiskCalculationModule.RiskCalculation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class CalculationLogic {
    //Получаем входные данные от пользователя (Получаем предприятие и цеха(пока что))
    //Из данных по цеху и предприятию получаем информацию по оборудованию и веществу, которое хранится
    //Также получаем информацию по базовым событиям
    @Autowired
    private EmergencyService emergencyService;
    @Autowired
    private TerritoryRepo territoryRepo;
    @Autowired
    private PotentiallyDangerousSituationRepo pdsRepo;
    @Autowired
    private CalculationRepo calculationRepo;
    @Autowired
    private ExposureTypeRepo exposureTypeRepo;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private Amount amount;
    @Autowired
    private CloudCombustionModeRepo cloudCombustionModeRepo;

    private ArrayList<EmergencySubTypeDamageCalculation> results;
    private Calculation calc;


    public void calculate(CalculationStartRequest startData) {
        Substance substance = startData.getEquipmentInDepartment().getSubstance();
        EquipmentClass equipmentClass = startData.getEquipmentInDepartment().getEquipmentClass();
        DestructionType destructionType = startData.getDestructionType();

        List<PotentiallyDangerousSituation> list = pdsRepo.findByEquipmentTypeIdAndEventIdAndDestructionTypeId(equipmentClass.getEquipmentType().getId(),
                        startData.getEvent().getId(), destructionType.getId());
        PotentiallyDangerousSituation pds = null;

        if(!destructionType.isFull()) {
            double maxDiameter = list.stream()
                    .map(PotentiallyDangerousSituation::getHoleDiameter)
                    .max(Double::compareTo)
                    .get();
            //System.out.println(maxDiameter);
            //System.out.println(startData.getCalculationVariableParameters().getHoleDiameter());
            if (maxDiameter < startData.getCalculationVariableParameters().getHoleDiameter()) {
                pds = list.stream()
                        .max(Comparator.comparingDouble(PotentiallyDangerousSituation::getHoleDiameter)).get();
            } else {
                pds = list.stream().filter(x -> x.getHoleDiameter() > startData.getCalculationVariableParameters().getHoleDiameter())
                        .min(Comparator.comparingDouble(PotentiallyDangerousSituation::getHoleDiameter)).get();
            }
        } else {
            pds = list.get(0);
        }
        //territoryRepo.findByEnterpriseId(startData.getEnterprise().getId());
        List<Territory> territoryList = territoryRepo.findByEnterpriseId(startData.getEnterprise().getId());

        emergencyService.getEmergencyRelatedData(equipmentClass.getEquipmentType().getId(), startData.getEvent().getId(),
                substance.getSubstanceType().getId(), destructionType.getId());

        List<EmergencySubType> emergencies = emergencyService.getEmergencyTree().stream()
                .map(EmergencyScenario::getEmergencySubType)
                .collect(Collectors.toList());

        List<BaseModel> mathModelsOfEmergencies = convertEmergenciesToMathModels(emergencies);

        amount.calculate(equipmentClass, startData.getEquipmentInDepartment().getDepartment(), substance,
                startData.getEquipmentInDepartment().getFullnessPercent()/100,startData.getCalculationVariableParameters());

        ProbitFunctionsToExposureProbability pf = new ProbitFunctionsToExposureProbability();
        List<List<Double>> exposureProbabilitiesForAllEmergencies = new ArrayList<>();
        for (BaseModel bm : mathModelsOfEmergencies) {
            bm.calculate(substance, amount, startData.getEquipmentInDepartment().getDepartment(),
                    this.amount.getCoefficients(), startData.getEnterprise(), startData.getCalculationVariableParameters(), cloudCombustionModeRepo);
            exposureProbabilitiesForAllEmergencies.add(pf.convert(bm.getProbitFunctionValues(amount.getRadiusArray())));
        }
        RiskCalculation rk = new RiskCalculation();
        rk.calculate(pds.getDepressurizationFrequency(),
                emergencyService.getEmergencyTree().stream().map(EmergencyScenario::getProbability).collect(Collectors.toList()),
                exposureProbabilitiesForAllEmergencies, amount.getRadiusArray(), territoryList, startData.getEnterprise().getArea(),
                startData.getCalculationVariableParameters().getNumberOfWorkers());

        this.calc = new Calculation();
        calc.setTime(new Timestamp(System.currentTimeMillis()));
        System.out.println(startData.getLogin());
        calc.setUser(userRepo.findByLogin(startData.getLogin()).get());
        calc.setMatterQuantity(amount.getMass());
        this.calc = calculationRepo.saveAndFlush(calc);
        //calc.setMatterConsumption(amount.getProductConsumption()); когда разберусь, когда этот параметр есть, а когда нет

        //Здесь мы складываем получившиеся результаты в объекты типа EmergencySubTypeDamageCalculation
        //То есть берём emergencies(subType), сгенерированные радиусы в amount,берём emergencies и damagingFactors
        //делаем из них ключ и засовываем в массив
        //Массив отправляем клиенту, и записываем его в базу
        results = new ArrayList<>();
        for (int j = 0; j < emergencies.size(); j++) {
            if (mathModelsOfEmergencies.get(j).getType().equals("Взрыв")) {
                List<ExposureType> explosionExposures = exposureTypeRepo.findByEmergencyId(emergencies.get(j).getEmergency().getId());
                ArrayList<Double> tmpPressure = ((BaseExplosionModel) mathModelsOfEmergencies.get(j)).getExcessPressure();
                ArrayList<Double> tmpImpulse = ((BaseExplosionModel) mathModelsOfEmergencies.get(j)).getImpulse();
                ArrayList<Double> probits = mathModelsOfEmergencies.get(j).getProbitFunctionValues(amount.getRadiusArray());
                ArrayList<Double> potentialRisksForEmergencies = rk.getPotentialRiskForEmergencySubtypes().get(j);
                List<Double> exposureProbabilities = exposureProbabilitiesForAllEmergencies.get(j);
                for (int i = 0; i < amount.getRadiusArray().size(); i++) {
                    double radiusKey = amount.getRadiusArray().get(i);
                    long emergencyId = emergencies.get(j).getId();
                    ExposureType impulse = explosionExposures.stream()
                            .filter(x -> x.getName().equals("Импульс"))
                            .findFirst().get();
                    ExposureType pressure = explosionExposures.stream()
                            .filter(x -> x.getName().equals("Избыточное давление"))
                            .findFirst().get();
                    long calculationId = this.calc.getId();
                    DamagingExposureCalculationKey pressureCompositeKey =
                            new DamagingExposureCalculationKey(calculationId, emergencyId, pressure.getId(), radiusKey);
                    EmergencySubTypeDamageCalculation pressureDamageCalculation = new EmergencySubTypeDamageCalculation();
                    pressureDamageCalculation.setCalculation(this.calc);
                    pressureDamageCalculation.setEmergencySubType(emergencies.get(j));
                    pressureDamageCalculation.setExposureType(pressure);
                    pressureDamageCalculation.setDamagingExposureCalculationKey(pressureCompositeKey);
                    pressureDamageCalculation.setValue(tmpPressure.get(i));
                    pressureDamageCalculation.setProbitFunctionValue(probits.get(i));
                    pressureDamageCalculation.setProbability(exposureProbabilities.get(i));
                    pressureDamageCalculation.setPotentialRisk(potentialRisksForEmergencies.get(i));
                    results.add(pressureDamageCalculation);

                    DamagingExposureCalculationKey impulseCompositeKey =
                            new DamagingExposureCalculationKey(calculationId, emergencyId, impulse.getId(), radiusKey);
                    EmergencySubTypeDamageCalculation impulseDamageCalculation = new EmergencySubTypeDamageCalculation();
                    impulseDamageCalculation.setCalculation(this.calc);
                    impulseDamageCalculation.setEmergencySubType(emergencies.get(j));
                    impulseDamageCalculation.setExposureType(impulse);
                    impulseDamageCalculation.setDamagingExposureCalculationKey(impulseCompositeKey);
                    impulseDamageCalculation.setValue(tmpImpulse.get(i));
                    impulseDamageCalculation.setProbitFunctionValue(probits.get(i));
                    impulseDamageCalculation.setProbability(exposureProbabilities.get(i));
                    impulseDamageCalculation.setPotentialRisk(potentialRisksForEmergencies.get(i));
                    results.add(pressureDamageCalculation);
                }
            } else if (mathModelsOfEmergencies.get(j).getType().equals("Пожар")) {
                List<ExposureType> heatExposures = exposureTypeRepo.findByEmergencyId(emergencies.get(j).getEmergency().getId());
                ArrayList<Double> tmpHeat = ((BaseFireModel) mathModelsOfEmergencies.get(j)).getThermalRadiationIntensty();
                ArrayList<Double> probits = mathModelsOfEmergencies.get(j).getProbitFunctionValues(amount.getRadiusArray());
                ArrayList<Double> potentialRisksForEmergencies = rk.getPotentialRiskForEmergencySubtypes().get(j);
                List<Double> exposureProbabilities = exposureProbabilitiesForAllEmergencies.get(j);
                for (int i = 0; i < amount.getRadiusArray().size(); i++) {
                    double radiusKey = amount.getRadiusArray().get(i);
                    long emergencyId = emergencies.get(j).getId();
                    ExposureType heat = heatExposures.stream()
                            .filter(x -> x.getName().equals("Интенсивность теплового излучения"))
                            .findFirst().get();
                    long calculationId = this.calc.getId();

                    DamagingExposureCalculationKey heatCompositeKey =
                            new DamagingExposureCalculationKey(calculationId, emergencyId, heat.getId(), radiusKey);
                    EmergencySubTypeDamageCalculation heatExposure = new EmergencySubTypeDamageCalculation();
                    heatExposure.setCalculation(this.calc);
                    heatExposure.setEmergencySubType(emergencies.get(j));
                    heatExposure.setExposureType(heat);
                    heatExposure.setDamagingExposureCalculationKey(heatCompositeKey);
                    heatExposure.setValue(tmpHeat.get(i));
                    heatExposure.setProbitFunctionValue(probits.get(i));
                    heatExposure.setProbability(exposureProbabilities.get(i));
                    heatExposure.setPotentialRisk(potentialRisksForEmergencies.get(i));
                    results.add(heatExposure);
                }
            } else {
                List<ExposureType> flashExposures = exposureTypeRepo.findByEmergencyId(emergencies.get(j).getEmergency().getId());
                ArrayList<Double> probits = mathModelsOfEmergencies.get(j).getProbitFunctionValues(amount.getRadiusArray());
                ArrayList<Double> potentialRisksForEmergencies = rk.getPotentialRiskForEmergencySubtypes().get(j);
                List<Double> exposureProbabilities = exposureProbabilitiesForAllEmergencies.get(j);
                for (int i = 0; i < amount.getRadiusArray().size(); i++) {
                    double radiusKey = amount.getRadiusArray().get(i);
                    long emergencyId = emergencies.get(j).getId();
                    ExposureType flash = flashExposures.stream()
                            .filter(x -> x.getName().equals("Страшная смерть"))
                            .findFirst().get();
                    long calculationId = this.calc.getId();

                    DamagingExposureCalculationKey flashCompositeKey =
                            new DamagingExposureCalculationKey(calculationId, emergencyId, flash.getId(), radiusKey);
                    EmergencySubTypeDamageCalculation flashExposure = new EmergencySubTypeDamageCalculation();
                    flashExposure.setCalculation(this.calc);
                    flashExposure.setEmergencySubType(emergencies.get(j));
                    flashExposure.setExposureType(flash);
                    flashExposure.setDamagingExposureCalculationKey(flashCompositeKey);
                    flashExposure.setValue(-1);
                    flashExposure.setProbitFunctionValue(probits.get(i));
                    flashExposure.setProbability(exposureProbabilities.get(i));
                    flashExposure.setPotentialRisk(potentialRisksForEmergencies.get(i));
                    results.add(flashExposure);
                }
            }
        }
    }

    private List<BaseModel> convertEmergenciesToMathModels(List<EmergencySubType> emergencies) {
        List<BaseModel> tmp = new ArrayList<>();
        for (EmergencySubType emergencySubType : emergencies) {
            switch (emergencySubType.getName()) {
                case ("Огненный шар"): {
                    tmp.add(new FireBall());
                    break;
                }
                case ("Факельное горение"): {
                    tmp.add(new TorchFire());
                    break;
                }
                case ("Сгорание паровоздушного облака"): {
                    tmp.add(new VaporExplosion());
                    break;
                }
                case ("Пожар пролива"): {
                    tmp.add(new StraightFire());
                    break;
                }
                case ("Взырыв резервуара с перегретой жидкостью"): {
                    tmp.add(new OverHeatedReservoirExplosion());
                    break;
                }
                case ("Пожар-вспышка"): {
                    tmp.add(new FlashFire());
                    break;
                }
            }
        }
        return tmp;
    }
}
