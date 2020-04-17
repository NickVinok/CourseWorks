package DataBase.Repo;

import DataBase.Model.EmergencyScenario;
import DataBase.Model.Keys.EmergencyScenarioKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmergencyScenarioRepo extends JpaRepository<EmergencyScenario, EmergencyScenarioKey> {
    List<EmergencyScenario> findByEmergencyScenarioKey_SubstanceIdAndEmergencyScenarioKey_DestructionTypeId(long substanceId, long destructionTypeId);
}
