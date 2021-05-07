package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.CloudCombustionMode;
import com.diploma.Diploma.DataBase.Model.Emergency;
import com.diploma.Diploma.DataBase.Repo.EmergencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emergency")
public class EmergencyController {
    @Autowired
    EmergencyRepo repo;

    @PostMapping("/get")
    public Optional<Emergency> getEmergency(@RequestBody Emergency emergency){
        return repo.findById(emergency.getId());
    }

    @GetMapping()
    public List<Emergency> getEmergencies(){
        return repo.findAll();
    }

    @GetMapping("/getNew")
    public Emergency getNewEmergency(){
        return new Emergency();
    }

    @PostMapping("/create")
    public ResponseEntity<Emergency> newEmergency(@RequestBody Emergency emergency){
        Optional<Emergency> tmp = repo.findById(emergency.getId());
        if(tmp.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else{
            return ResponseEntity.ok(repo.save(emergency));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Emergency> updateEmergency(@RequestBody Emergency emergency){
        Optional<Emergency> tmp = repo.findById(emergency.getId());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(emergency));
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<Emergency> deleteEmergency(@RequestBody Emergency emergency){
        Optional<Emergency> tmp = repo.findById(emergency.getId());
        if(tmp.isPresent()){
            repo.deleteById(emergency.getId());
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }
}

