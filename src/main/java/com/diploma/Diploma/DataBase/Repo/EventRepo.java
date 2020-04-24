package com.diploma.Diploma.DataBase.Repo;

import com.diploma.Diploma.DataBase.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepo extends JpaRepository<Event, Long> {
}
