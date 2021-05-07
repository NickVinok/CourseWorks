package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.CloudCombustionMode;
import com.diploma.Diploma.DataBase.Model.Keys.CloudCombustionModeKey;
import com.diploma.Diploma.DataBase.Model.RolesTableInteraction;
import com.diploma.Diploma.DataBase.Repo.RolesTableInteractionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rolesTableInteraction")
public class RolesTableInteractionController {
    @Autowired
    RolesTableInteractionRepo repo;

    @PostMapping("/get")
    public Optional<RolesTableInteraction> getRolesTableInteraction(@RequestBody RolesTableInteraction rti) {
        return repo.findById(rti.getRolesTableInteractionKey());
    }

    @GetMapping()
    public List<RolesTableInteraction> getRolesTableInteractions(){
        return repo.findAll();
    }

    @GetMapping("/getNew")
    public RolesTableInteraction getNewRolesTableInteraction(){
        return new RolesTableInteraction();
    }

    @PostMapping("/create")
    public ResponseEntity<RolesTableInteraction> newRolesTableInteraction(@RequestBody RolesTableInteraction rti){
        Optional<RolesTableInteraction> tmp = repo.findById(rti.getRolesTableInteractionKey());
        if(tmp.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else{
            return ResponseEntity.ok(repo.save(rti));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<RolesTableInteraction> updateRolesTableInteraction(@RequestBody RolesTableInteraction rti){
        Optional<RolesTableInteraction> tmp = repo.findById(rti.getRolesTableInteractionKey());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(rti));
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<RolesTableInteraction> deleteRolesTableInteraction(@RequestBody RolesTableInteraction rti){
        Optional<RolesTableInteraction> tmp = repo.findById(rti.getRolesTableInteractionKey());
        if(tmp.isPresent()){
            repo.deleteById(rti.getRolesTableInteractionKey());
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
