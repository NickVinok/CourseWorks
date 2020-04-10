package DataBase.Repo;

import DataBase.Model.EmergencySubTypeCoefficients;
import DataBase.Model.Keys.EmergencySubTypeCoefficientsKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencySubTypeCoefficientsRepo extends JpaRepository<EmergencySubTypeCoefficients, EmergencySubTypeCoefficientsKey> {
}
