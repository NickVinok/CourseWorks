package DataBase.Repo;

import DataBase.Model.Territory;
import DataBase.Model.User;
import DataBase.Model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerritoryRepo extends JpaRepository<Territory, Long> {
    List<Territory> findByEnterpriseId(long enterpriseId);
}
