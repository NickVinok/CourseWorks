package DataBase.Repo;

import DataBase.Model.Right;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RightRepo extends JpaRepository<Right, Long> {
}
