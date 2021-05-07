package com.diploma.Diploma.DataBase.Repo;

import com.diploma.Diploma.DataBase.Model.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteractionRepo extends JpaRepository<Interaction,Long> {
}
