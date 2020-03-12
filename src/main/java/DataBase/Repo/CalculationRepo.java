package DataBase.Repo;

import DataBase.Model.Calculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationRepo extends JpaRepository<Calculation, Long> {
}
