package DataBase.Repo;

import DataBase.Model.DestructionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestructionTypeRepo extends JpaRepository<DestructionType, Long> {
}
