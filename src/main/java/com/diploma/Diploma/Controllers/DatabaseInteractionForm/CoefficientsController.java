package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.Coefficients;
import com.diploma.Diploma.DataBase.Model.Keys.CloudCombustionModeKey;
import com.diploma.Diploma.DataBase.Repo.CoefficientsRepo;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/create")
    public ResponseEntity<Coefficients> newCoefficient(@RequestBody Coefficients coefficients){
        return ResponseEntity.ok(repo.save(coefficients));
    }

    @PostMapping("/update")
    public ResponseEntity<Coefficients> updateCoefficient(@RequestBody Coefficients coefficients){
        Optional<Coefficients> tmp = repo.findById(coefficients.getId());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(coefficients));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/delete")
    public void deleteCoefficient(@RequestBody Coefficients id){
        repo.deleteById(id.getId());
    }
}
