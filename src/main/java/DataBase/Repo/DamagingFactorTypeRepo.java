package DataBase.Repo;

import DataBase.Model.DamagingFactor;
import DataBase.Model.DamagingFactorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DamagingFactorTypeRepo extends JpaRepository<DamagingFactorType, Long> {
}
