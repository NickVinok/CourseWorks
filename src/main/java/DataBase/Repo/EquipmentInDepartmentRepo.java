package DataBase.Repo;

import DataBase.Model.EquipmentInDepartment;
import DataBase.Model.Keys.EquipmentInDepartmentKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentInDepartmentRepo extends JpaRepository<EquipmentInDepartment, EquipmentInDepartmentKey> {
}
