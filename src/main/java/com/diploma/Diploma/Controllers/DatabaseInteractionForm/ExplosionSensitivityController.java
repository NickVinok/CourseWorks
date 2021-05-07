package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.CloudCombustionMode;
import com.diploma.Diploma.DataBase.Model.Event;
import com.diploma.Diploma.DataBase.Model.ExplosionSensitivity;
import com.diploma.Diploma.DataBase.Repo.EventRepo;
import com.diploma.Diploma.DataBase.Repo.ExplosionSensitivityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/explosionSensitivity")
public class ExplosionSensitivityController {
    @Autowired
    ExplosionSensitivityRepo repo;

    @PostMapping("/get")
    public Optional<ExplosionSensitivity> getEvent(@RequestBody ExplosionSensitivity event){
        return repo.findById(event.getId());
    }

    @GetMapping()
    public List<ExplosionSensitivity> getEvent(){
        return repo.findAll();
    }

    @GetMapping("/getNew")
    public ExplosionSensitivity getNewExplosionSensitivity(){
        return new ExplosionSensitivity();
    }

    @PostMapping("/create")
    public ResponseEntity<ExplosionSensitivity> newEvent(@RequestBody ExplosionSensitivity event){
        Optional<ExplosionSensitivity> tmp = repo.findById(event.getId());
        if(tmp.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else{
            return ResponseEntity.ok(repo.save(event));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ExplosionSensitivity> updateEvent(@RequestBody ExplosionSensitivity event){
        Optional<ExplosionSensitivity> tmp = repo.findById(event.getId());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(event));
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<ExplosionSensitivity> deleteEvent(@RequestBody ExplosionSensitivity event){
        Optional<ExplosionSensitivity> tmp = repo.findById(event.getId());
        if(tmp.isPresent()){
            repo.deleteById(event.getId());
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
