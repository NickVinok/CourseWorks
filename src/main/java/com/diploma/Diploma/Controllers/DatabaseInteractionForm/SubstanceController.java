package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

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

    @GetMapping("/{id}")
    public Substance getSubstance(@PathVariable long id){
        return repo.findById(id).get();
    }

    @GetMapping()
    public List<Substance> getSubstance(){
        return repo.findAll();
    }

    @PostMapping
    public Substance newSubstance(@RequestBody Substance substance){
        return repo.save(substance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Substance> updateSubstance(@RequestBody Substance substance, @PathVariable  long id){
        Optional<com.diploma.Diploma.DataBase.Model.Substance> tmp = repo.findById(id);
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(substance));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteSubstance(@PathVariable long id){
        repo.deleteById(id);
    }
}
