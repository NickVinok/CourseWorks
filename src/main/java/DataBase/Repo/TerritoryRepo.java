package DataBase.Repo;

import DataBase.Model.Territory;
import DataBase.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerritoryRepo extends JpaRepository<Territory, Long> {

}
