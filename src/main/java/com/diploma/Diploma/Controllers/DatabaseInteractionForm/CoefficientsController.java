package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.Coefficients;
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

    @GetMapping("/{id}")
    public Coefficients getCoefficient(@PathVariable long id){
        return repo.findById(id).get();
    }

    @GetMapping()
    public List<Coefficients> getCoefficients(){
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<Coefficients> newCoefficient(@RequestBody Coefficients coefficients){
        System.out.println(coefficients);
        return ResponseEntity.ok(repo.save(coefficients));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coefficients> updateCoefficient(@RequestBody Coefficients coefficients, @PathVariable  long id){
        Optional<Coefficients> tmp = repo.findById(id);
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(coefficients));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCoefficient(@PathVariable long id){
        repo.deleteById(id);
    }
}
