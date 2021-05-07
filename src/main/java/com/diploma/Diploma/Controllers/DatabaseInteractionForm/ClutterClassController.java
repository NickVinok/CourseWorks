package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.CalculationVariableParameters;
import com.diploma.Diploma.DataBase.Model.CloudCombustionMode;
import com.diploma.Diploma.DataBase.Model.ClutterClass;
import com.diploma.Diploma.DataBase.Model.Event;
import com.diploma.Diploma.DataBase.Repo.ClutterClassRepo;
import com.diploma.Diploma.DataBase.Repo.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clutterClass")
public class ClutterClassController {
    @Autowired
    ClutterClassRepo repo;

    @PostMapping("/get")
    public Optional<ClutterClass> getEvent(@RequestBody ClutterClass c){
        return repo.findById(c.getId());
    }

    @GetMapping()
    public List<ClutterClass> getEvent(){
        return repo.findAll();
    }

    @GetMapping("/getNew")
    public ClutterClass getNewClutterClass(){
        return new ClutterClass();
    }

    @PostMapping("/create")
    public ResponseEntity<ClutterClass> newEvent(@RequestBody ClutterClass cc){
        Optional<ClutterClass> tmp = repo.findById(cc.getId());
        if(tmp.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else{
            return ResponseEntity.ok(repo.save(cc));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ClutterClass> updateEvent(@RequestBody ClutterClass event){
        Optional<ClutterClass> tmp = repo.findById(event.getId());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(event));
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<ClutterClass> deleteEvent(@RequestBody ClutterClass cc){
        Optional<ClutterClass> tmp = repo.findById(cc.getId());
        if(tmp.isPresent()){
            repo.deleteById(cc.getId());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
