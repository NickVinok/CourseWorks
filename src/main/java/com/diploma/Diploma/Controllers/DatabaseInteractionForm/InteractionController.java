package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.CloudCombustionMode;
import com.diploma.Diploma.DataBase.Model.Interaction;
import com.diploma.Diploma.DataBase.Model.Keys.CloudCombustionModeKey;
import com.diploma.Diploma.DataBase.Repo.InteractionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/interaction")
public class InteractionController {
    @Autowired
    InteractionRepo repo;

    @PostMapping("/get")
    public Optional<Interaction> getInteraction(@RequestBody Interaction i) {
        return repo.findById(i.getId());
    }

    @GetMapping()
    public List<Interaction> getInteractions(){
        return repo.findAll();
    }

    @GetMapping("/getNew")
    public Interaction getNewCInteraction(){
        return new Interaction();
    }

    @PostMapping("/create")
    public ResponseEntity<Interaction> newInteraction(@RequestBody Interaction i){
        Optional<Interaction> tmp = repo.findById(i.getId());
        if(tmp.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else{
            return ResponseEntity.ok(repo.save(i));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Interaction> updateInteraction(@RequestBody Interaction i){
        Optional<Interaction> tmp = repo.findById(i.getId());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(i));
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<Interaction> deleteInteraction(@RequestBody Interaction i){
        Optional<Interaction> tmp = repo.findById(i.getId());
        if(tmp.isPresent()){
            repo.deleteById(i.getId());
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
