package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.SubstanceType;
import com.diploma.Diploma.DataBase.Repo.SubstanceTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/substanceType")
public class SubstanceTypeController {
    @Autowired
    SubstanceTypeRepo repo;

    @GetMapping("/{id}")
    public Optional<SubstanceType> getSubstanceType(@PathVariable long id){
        return repo.findById(id);
    }

    @GetMapping()
    public List<SubstanceType> getSubstanceType(){
        return repo.findAll();
    }

    @PostMapping
    public SubstanceType newSubstanceType(@RequestBody SubstanceType substance){
        return repo.save(substance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubstanceType> updateSubstanceType(@RequestBody SubstanceType substance, @PathVariable  long id){
        Optional<SubstanceType> tmp = repo.findById(id);
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(substance));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteSubstanceType(@PathVariable long id){
        repo.deleteById(id);
    }
}
