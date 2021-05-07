package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.CloudCombustionMode;
import com.diploma.Diploma.DataBase.Model.Coefficients;
import com.diploma.Diploma.DataBase.Model.Keys.CloudCombustionModeKey;
import com.diploma.Diploma.DataBase.Repo.CoefficientsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coefficients")
public class CoefficientsController {
    @Autowired
    CoefficientsRepo repo;

    @PostMapping("/get")
    public Optional<Coefficients> getCoefficient(@RequestBody Coefficients id){
        return repo.findById(id.getId());
    }

    @GetMapping()
    public List<Coefficients> getCoefficients(){
        return repo.findAll();
    }

    @GetMapping("/getNew")
    public Coefficients getNewCoefficients(){
        return new Coefficients();
    }

    @PostMapping("/create")
    public ResponseEntity<Coefficients> newCoefficient(@RequestBody Coefficients coefficients){
        Optional<Coefficients> tmp = repo.findById(coefficients.getId());
        if(tmp.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else{
            return ResponseEntity.ok(repo.save(coefficients));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Coefficients> updateCoefficient(@RequestBody Coefficients coefficients){
        Optional<Coefficients> tmp = repo.findById(coefficients.getId());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(coefficients));
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<Coefficients> deleteCoefficient(@RequestBody Coefficients coefficients){
        Optional<Coefficients> tmp = repo.findById(coefficients.getId());
        if(tmp.isPresent()){
            repo.deleteById(coefficients.getId());
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
