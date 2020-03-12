package DataBase.Repo;


import DataBase.Model.DamagingExposureCalculation;
import DataBase.Model.Keys.DamagingExposureCalculationKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DamagingExposureCalculationRepo
        extends JpaRepository<DamagingExposureCalculation, DamagingExposureCalculationKey> {
}
