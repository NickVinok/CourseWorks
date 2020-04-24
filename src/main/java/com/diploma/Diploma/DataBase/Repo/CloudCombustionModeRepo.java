package com.diploma.Diploma.DataBase.Repo;

import com.diploma.Diploma.DataBase.Model.CloudCombustionMode;
import com.diploma.Diploma.DataBase.Model.Keys.CloudCombustionModeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CloudCombustionModeRepo extends JpaRepository<CloudCombustionMode, CloudCombustionModeKey> {
}
