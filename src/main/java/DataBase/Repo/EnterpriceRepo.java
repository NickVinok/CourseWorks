package DataBase.Repo;

import DataBase.Model.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriceRepo extends JpaRepository<Enterprise, Long> {
}
