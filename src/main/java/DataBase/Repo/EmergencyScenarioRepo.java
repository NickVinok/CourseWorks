package DataBase.Repo;

import DataBase.Model.EmergencyScenario;
import DataBase.Model.Keys.EmergencyScenarioKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencyScenarioRepo extends JpaRepository<EmergencyScenario, EmergencyScenarioKey> {
}
