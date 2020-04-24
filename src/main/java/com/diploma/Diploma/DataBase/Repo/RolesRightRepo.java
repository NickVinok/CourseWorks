package com.diploma.Diploma.DataBase.Repo;

import com.diploma.Diploma.DataBase.Model.Keys.RolesRightKey;
import com.diploma.Diploma.DataBase.Model.RolesRight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRightRepo extends JpaRepository<RolesRight, RolesRightKey> {
}
