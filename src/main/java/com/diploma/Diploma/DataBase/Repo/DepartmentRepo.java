package com.diploma.Diploma.DataBase.Repo;

import com.diploma.Diploma.DataBase.Model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
    Optional<List<Department>> findByEnterpriseId(long enterpriseId);
}
