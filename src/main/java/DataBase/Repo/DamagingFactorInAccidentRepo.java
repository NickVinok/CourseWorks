package DataBase.Repo;

import DataBase.Model.DamagingFactorInAccident;
import DataBase.Model.Keys.DamagingFactorInAccidentKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DamagingFactorInAccidentRepo extends JpaRepository<DamagingFactorInAccident, DamagingFactorInAccidentKey> {
}
