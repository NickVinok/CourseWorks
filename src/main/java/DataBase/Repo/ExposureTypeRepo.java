package DataBase.Repo;

import DataBase.Model.ExposureType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExposureTypeRepo extends JpaRepository<ExposureType, Long> {
    List<ExposureType> findByEmergencyId(long emergencyId);
}
