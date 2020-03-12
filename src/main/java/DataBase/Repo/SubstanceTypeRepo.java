package DataBase.Repo;

import DataBase.Model.SubstanceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubstanceTypeRepo extends JpaRepository<SubstanceType, Long> {
}
