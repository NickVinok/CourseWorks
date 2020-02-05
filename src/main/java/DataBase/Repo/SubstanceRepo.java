package DataBase.Repo;

import DataBase.Model.Substance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubstanceRepo extends JpaRepository<Substance, Long> {
}
