package com.diploma.Diploma.DataBase.Repo;

import com.diploma.Diploma.DataBase.Model.EmergencyScenario;
import com.diploma.Diploma.DataBase.Model.Keys.EmergencyScenarioKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmergencyScenarioRepo extends JpaRepository<EmergencyScenario, EmergencyScenarioKey> {
    List<EmergencyScenario> findByEmergencyScenarioKey_SubstanceTypeIdAndEmergencyScenarioKey_DestructionTypeId(long substanceId, long destructionTypeId);
}
