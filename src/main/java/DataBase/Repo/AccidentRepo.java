package DataBase.Repo;

import DataBase.Model.Accident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccidentRepo extends JpaRepository<Accident, Long> {
}
