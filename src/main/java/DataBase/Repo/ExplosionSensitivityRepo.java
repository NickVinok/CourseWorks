package DataBase.Repo;

import DataBase.Model.ExplosionSensitivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExplosionSensitivityRepo extends JpaRepository<ExplosionSensitivity, Long> {
}
