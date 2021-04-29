package com.diploma.Diploma.DataBase.Repo;

import com.diploma.Diploma.DataBase.Model.EmergencyScenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmergencyScenarioRepo extends JpaRepository<EmergencyScenario, Long> {
    List<EmergencyScenario> findBySubstanceTypeIdAndDestructionTypeIdAndEventId(long substanceId, boolean destructionTypeId, long eventId);
}
