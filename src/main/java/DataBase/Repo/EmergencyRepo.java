package DataBase.Repo;

import DataBase.Model.Emergency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencyRepo extends JpaRepository<Emergency, Long> {
}
