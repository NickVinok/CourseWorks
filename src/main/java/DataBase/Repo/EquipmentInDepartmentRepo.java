package DataBase.Repo;

import DataBase.Model.EquipmentInDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipmentInDepartmentRepo extends JpaRepository<EquipmentInDepartment, Long> {
    Optional<List<EquipmentInDepartment>> findByEDepartmentIdAndSubstanceIdNot(long departmentId, long substanceId);
}
