package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.CloudCombustionMode;
import com.diploma.Diploma.DataBase.Model.SubstanceType;
import com.diploma.Diploma.DataBase.Repo.SubstanceTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/substanceType")
public class SubstanceTypeController {
    @Autowired
    SubstanceTypeRepo repo;

    @PostMapping("/get")
    public Optional<SubstanceType> getSubstanceType(@RequestBody SubstanceType substance){
        return repo.findById(substance.getId());
    }

    @GetMapping()
    public List<SubstanceType> getSubstanceType(){
        return repo.findAll();
    }

    @GetMapping("/getNew")
    public SubstanceType getNewSubstanceType(){
        return new SubstanceType();
    }

    @PostMapping("/create")
    public ResponseEntity<SubstanceType> newSubstanceType(@RequestBody SubstanceType substance){
        Optional<SubstanceType> tmp = repo.findById(substance.getId());
        if(tmp.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else{
            return ResponseEntity.ok(repo.save(substance));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<SubstanceType> updateSubstanceType(@RequestBody SubstanceType substance){
        Optional<SubstanceType> tmp = repo.findById(substance.getId());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(substance));
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<SubstanceType> deleteSubstanceType(@RequestBody SubstanceType substance){
        Optional<SubstanceType> tmp = repo.findById(substance.getId());
        if(tmp.isPresent()){
            repo.deleteById(substance.getId());
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
