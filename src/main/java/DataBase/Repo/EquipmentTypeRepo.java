package DataBase.Repo;

import DataBase.Model.EquipmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentTypeRepo extends JpaRepository<EquipmentType, Long> {
}
