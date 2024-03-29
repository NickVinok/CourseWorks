package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.CloudCombustionMode;
import com.diploma.Diploma.DataBase.Model.GeneralCoefficients;
import com.diploma.Diploma.DataBase.Repo.GeneralCoefficientsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/generalCoefficients")
public class GeneralCoefficientsController {
    @Autowired
    GeneralCoefficientsRepo repo;

    @PostMapping("/get")
    public Optional<GeneralCoefficients> getGeneralCoefficient(@RequestBody GeneralCoefficients generalCoefficients){
        return repo.findById(generalCoefficients.getId());
    }

    @GetMapping()
    public List<GeneralCoefficients> getGeneralCoefficients(){
        return repo.findAll();
    }

    @GetMapping("/getNew")
    public GeneralCoefficients getNewGeneralCoefficients(){
        return new GeneralCoefficients();
    }

    @PostMapping("/create")
    public GeneralCoefficients newGeneralCoefficients(@RequestBody GeneralCoefficients generalCoefficients){
        return repo.save(generalCoefficients);
    }

    @PostMapping("/update")
    public ResponseEntity<GeneralCoefficients> updateGeneralCoefficients(@RequestBody GeneralCoefficients generalCoefficients){
        Optional<GeneralCoefficients> tmp = repo.findById(generalCoefficients.getId());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(generalCoefficients));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/delete")
    public void deleteGeneralCoefficients(@RequestBody GeneralCoefficients generalCoefficients){
        repo.deleteById(generalCoefficients.getId());
    }
}
