package com.diploma.Diploma.DataBase.Repo;

import com.diploma.Diploma.DataBase.Model.Coefficients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoefficientsRepo extends JpaRepository<Coefficients, Long> {
}
