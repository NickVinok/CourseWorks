package DataBase.Repo;

import DataBase.Model.GeneralCoefficients;
import DataBase.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralCoefficientsRepo extends JpaRepository<GeneralCoefficients, Long> {
}
