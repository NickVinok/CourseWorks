package DataBase.Repo;

import DataBase.Model.ExposureType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExposureTypeRepo extends JpaRepository<ExposureType, Long> {
}
