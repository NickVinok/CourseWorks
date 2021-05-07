package com.diploma.Diploma.DataBase.Repo;

import com.diploma.Diploma.DataBase.Model.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TablesRepo extends JpaRepository<Tables,Long> {
}
