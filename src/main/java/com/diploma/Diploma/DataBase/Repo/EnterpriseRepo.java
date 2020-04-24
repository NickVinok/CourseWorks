package com.diploma.Diploma.DataBase.Repo;

import com.diploma.Diploma.DataBase.Model.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseRepo extends JpaRepository<Enterprise, Long> {
}
