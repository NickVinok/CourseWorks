package DataBase.Repo;

import DataBase.Model.Coefficients;
import DataBase.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoefficientsRepo extends JpaRepository<Coefficients, Long> {
}
