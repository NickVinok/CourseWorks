package DataBase.Repo;

import DataBase.Model.DamagingFactor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DamagingFactorRepo extends JpaRepository<DamagingFactor,Long> {
}
