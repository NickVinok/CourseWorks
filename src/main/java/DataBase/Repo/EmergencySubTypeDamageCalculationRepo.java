package DataBase.Repo;


import DataBase.Model.EmergencySubTypeDamageCalculation;
import DataBase.Model.Keys.DamagingExposureCalculationKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencySubTypeDamageCalculationRepo
        extends JpaRepository<EmergencySubTypeDamageCalculation, DamagingExposureCalculationKey> {
}
