package com.diploma.Diploma.DataBase.Repo;

import com.diploma.Diploma.DataBase.Model.EmergencyScenarioNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScenarioNodeRepo extends JpaRepository<EmergencyScenarioNode, Long> {
    List<EmergencyScenarioNode> findByEmergencyScenarioId(long emergencyScenarioId);
}
