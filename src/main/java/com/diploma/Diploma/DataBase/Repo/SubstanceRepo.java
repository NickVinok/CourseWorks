package com.diploma.Diploma.DataBase.Repo;

import com.diploma.Diploma.DataBase.Model.Substance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubstanceRepo extends JpaRepository<Substance, Long> {
    Substance findByName(String name);
}
