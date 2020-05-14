package com.diploma.Diploma.DataBase.Repo;

import com.diploma.Diploma.DataBase.Model.Enterprise;
import com.diploma.Diploma.DataBase.Model.Territory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerritoryRepo extends JpaRepository<Territory, Long> {
    List<Territory> findByEnterpriseId(Enterprise enterprise);
}
