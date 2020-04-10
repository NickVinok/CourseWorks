package DataBase.Repo;

import DataBase.Model.PotentiallyDangerousSituation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PotentiallyDangerousSituationRepo extends JpaRepository<PotentiallyDangerousSituation, Long> {
}
