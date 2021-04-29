package com.diploma.Diploma.DataBase.Repo;

import com.diploma.Diploma.DataBase.Model.CalculationVariableParameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationVariableParametersRepo extends JpaRepository<CalculationVariableParameters, Long> {
}
