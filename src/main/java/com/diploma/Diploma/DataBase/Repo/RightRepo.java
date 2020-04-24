package com.diploma.Diploma.DataBase.Repo;

import com.diploma.Diploma.DataBase.Model.Rights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RightRepo extends JpaRepository<Rights, Long> {
}
