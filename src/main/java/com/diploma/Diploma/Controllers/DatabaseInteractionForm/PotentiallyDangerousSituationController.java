package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.CloudCombustionMode;
import com.diploma.Diploma.DataBase.Model.PotentiallyDangerousSituation;
import com.diploma.Diploma.DataBase.Repo.PotentiallyDangerousSituationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/potentiallyDangerousSituation")
public class  PotentiallyDangerousSituationController {
    @Autowired
    PotentiallyDangerousSituationRepo repo;

    @PostMapping("/get")
    public Optional<PotentiallyDangerousSituation> getPotentiallyDangerousSituation(
            @RequestBody PotentiallyDangerousSituation potentiallyDangerousSituation){
        return repo.findById(potentiallyDangerousSituation.getId());
    }

    @GetMapping()
    public List<PotentiallyDangerousSituation> getPotentiallyDangerousSituation(){
        return repo.findAll();
    }

    @GetMapping("/getNew")
    public PotentiallyDangerousSituation getNewPotentiallyDangerousSituation(){
        return new PotentiallyDangerousSituation();
    }

    @PostMapping("/create")
    public ResponseEntity<PotentiallyDangerousSituation> newPotentiallyDangerousSituation(
            @RequestBody PotentiallyDangerousSituation potentiallyDangerousSituation){
        Optional<PotentiallyDangerousSituation> tmp = repo.findById(potentiallyDangerousSituation.getId());
        if(tmp.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else{
            return ResponseEntity.ok(repo.save(potentiallyDangerousSituation));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<PotentiallyDangerousSituation> updatePotentiallyDangerousSituation(
            @RequestBody PotentiallyDangerousSituation potentiallyDangerousSituation){
        Optional<PotentiallyDangerousSituation> tmp = repo.findById(potentiallyDangerousSituation.getId());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(potentiallyDangerousSituation));
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<PotentiallyDangerousSituation> deletePotentiallyDangerousSituation(
            @RequestBody PotentiallyDangerousSituation potentiallyDangerousSituation
    ){
        Optional<PotentiallyDangerousSituation> tmp = repo.findById(potentiallyDangerousSituation.getId());
        if(tmp.isPresent()){
            repo.deleteById(potentiallyDangerousSituation.getId());
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }
}
