package DataBase.Repo;

import DataBase.Model.Keys.RiskInTerritoryKey;
import DataBase.Model.RiskInTerritory;
import DataBase.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiskInTerritoryRepo extends JpaRepository<RiskInTerritory, RiskInTerritoryKey> {
}
