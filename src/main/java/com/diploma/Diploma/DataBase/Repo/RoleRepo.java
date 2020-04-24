package com.diploma.Diploma.DataBase.Repo;

import com.diploma.Diploma.DataBase.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
}
