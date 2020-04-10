package DataBase.Repo;

import DataBase.Model.Keys.RolesRightKey;
import DataBase.Model.RolesRight;
import DataBase.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRightRepo extends JpaRepository<RolesRight, RolesRightKey> {
}
