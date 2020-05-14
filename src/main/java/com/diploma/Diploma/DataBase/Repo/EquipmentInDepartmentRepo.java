package com.diploma.Diploma.DataBase.Repo;

import com.diploma.Diploma.DataBase.Model.EquipmentInDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipmentInDepartmentRepo extends JpaRepository<EquipmentInDepartment, Long> {
    Optional<List<EquipmentInDepartment>> findByDepartmentIdAndSubstanceIdNot(long departmentId, long substanceId);
    Optional<List<EquipmentInDepartment>> findBySubstanceIdIsNotNullAndDepartmentIdIn(List<Long> departmentIds);
}
