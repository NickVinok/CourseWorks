package com.diploma.Diploma.DataBase.Repo;


import com.diploma.Diploma.DataBase.Model.EmergencySubTypeDamageCalculation;
import com.diploma.Diploma.DataBase.Model.Keys.DamagingExposureCalculationKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencySubTypeDamageCalculationRepo
        extends JpaRepository<EmergencySubTypeDamageCalculation, DamagingExposureCalculationKey> {
}
