package com.diploma.Diploma.DataBase.Repo;

import com.diploma.Diploma.DataBase.Model.ExposureType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExposureTypeRepo extends JpaRepository<ExposureType, Long> {
    List<ExposureType> findByEmergencyId(long emergencyId);
}
