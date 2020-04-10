package DataBase.Repo;

import DataBase.Model.EquipmentClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentClassRepo extends JpaRepository<EquipmentClass, Long> {
}
