package com.diploma.Diploma.DataBase.Repo;

import com.diploma.Diploma.DataBase.Model.EmergencySubType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencySubTypeRepo extends JpaRepository<EmergencySubType,Long> {
}
