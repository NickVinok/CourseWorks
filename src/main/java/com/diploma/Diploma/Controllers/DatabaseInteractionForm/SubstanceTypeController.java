package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.CloudCombustionMode;
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
    public SubstanceType newSubstanceType(@RequestBody SubstanceType substance){
        return repo.save(substance);
    }

    @PostMapping("/update")
    public ResponseEntity<SubstanceType> updateSubstanceType(@RequestBody SubstanceType substance){
        Optional<SubstanceType> tmp = repo.findById(substance.getId());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(substance));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/delete")
    public void deleteSubstanceType(@RequestBody SubstanceType substance){
        repo.deleteById(substance.getId());
    }
}
