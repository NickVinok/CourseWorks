package com.diploma.Diploma.DataBase.Repo;

import com.diploma.Diploma.DataBase.Model.Keys.RolesTableInteractionKey;
import com.diploma.Diploma.DataBase.Model.RolesTableInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface RolesTableInteractionRepo extends JpaRepository<RolesTableInteraction, RolesTableInteractionKey> {
    List<RolesTableInteraction> findByRoleIdAndHas(long rolesId, boolean has);
}
