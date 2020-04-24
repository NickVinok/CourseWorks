package com.diploma.Diploma.DataBase.Service;

import com.diploma.Diploma.DataBase.Model.EmergencyScenario;
import com.diploma.Diploma.DataBase.Model.PotentiallyDangerousSituation;
import com.diploma.Diploma.DataBase.Repo.EmergencyScenarioRepo;
import com.diploma.Diploma.DataBase.Repo.PotentiallyDangerousSituationRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class EmergencyService {
    @Autowired
    private EmergencyScenarioRepo scenarioRepo;
    @Autowired
    private PotentiallyDangerousSituationRepo pdsRepo;

    private List<EmergencyScenario> emergencyTree;

    public void getEmergencyRelatedData(long equipmentTypeId, long eventId, long substanceTypeId, long destructionTypeId){
        PotentiallyDangerousSituation pds = pdsRepo.findByEquipmentTypeIdAndEventIdAndDestructionTypeId(equipmentTypeId, eventId, destructionTypeId).get(0);
        this.emergencyTree = scenarioRepo
                .findByEmergencyScenarioKey_SubstanceTypeIdAndEmergencyScenarioKey_DestructionTypeId(substanceTypeId, destructionTypeId);
    }
}
