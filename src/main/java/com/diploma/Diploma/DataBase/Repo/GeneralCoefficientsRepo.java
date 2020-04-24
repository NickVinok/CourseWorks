package com.diploma.Diploma.DataBase.Repo;

import com.diploma.Diploma.DataBase.Model.GeneralCoefficients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralCoefficientsRepo extends JpaRepository<GeneralCoefficients, Long> {
}
