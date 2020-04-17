package DataBase.Service;

import DataBase.Model.EmergencyScenario;
import DataBase.Model.PotentiallyDangerousSituation;
import DataBase.Repo.EmergencyScenarioRepo;
import DataBase.Repo.PotentiallyDangerousSituationRepo;
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
                .findByEmergencyScenarioKey_SubstanceIdAndEmergencyScenarioKey_DestructionTypeId(substanceTypeId, destructionTypeId);
    }
}
