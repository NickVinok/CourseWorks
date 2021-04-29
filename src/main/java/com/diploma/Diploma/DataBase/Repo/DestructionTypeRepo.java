package com.diploma.Diploma.DataBase.Repo;

import com.diploma.Diploma.DataBase.Model.DestructionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestructionTypeRepo extends JpaRepository<DestructionType, Boolean> {
}
