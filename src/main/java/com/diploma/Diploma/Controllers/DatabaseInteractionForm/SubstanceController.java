package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.CloudCombustionMode;
import com.diploma.Diploma.DataBase.Model.Substance;
import com.diploma.Diploma.DataBase.Repo.SubstanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/substance")
public class SubstanceController {
    @Autowired
    SubstanceRepo repo;

    @PostMapping("/get")
    public Optional<Substance> getSubstance(@RequestBody Substance substance){
        return repo.findById(substance.getId());
    }

    @GetMapping()
    public List<Substance> getSubstance(){
        return repo.findAll();
    }

    @GetMapping("/getNew")
    public Substance getNewSubstance(){
        return new Substance();
    }

    @PostMapping("/create")
    public Substance newSubstance(@RequestBody Substance substance){
        return repo.save(substance);
    }

    @PostMapping("/update")
    public ResponseEntity<Substance> updateSubstance(@RequestBody Substance substance){
        Optional<com.diploma.Diploma.DataBase.Model.Substance> tmp = repo.findById(substance.getId());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(substance));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/delete")
    public void deleteSubstance(@RequestBody Substance substance){
        repo.deleteById(substance.getId());
    }
}
