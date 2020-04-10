package DataBase.Repo;

import DataBase.Model.User;
import DataBase.Model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepo extends JpaRepository<Zone, Long> {
}
