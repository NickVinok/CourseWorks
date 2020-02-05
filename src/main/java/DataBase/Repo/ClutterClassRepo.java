package DataBase.Repo;


import DataBase.Model.ClutterClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClutterClassRepo extends JpaRepository<ClutterClass, Long> {
}
