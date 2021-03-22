package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.ExposureType;
import com.diploma.Diploma.DataBase.Repo.ExposureTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exposureType")
public class ExposureTypeController {
    @Autowired
    ExposureTypeRepo repo;

    @GetMapping("/{id}")
    public Optional<ExposureType> getExposureType(@PathVariable long id){
        return repo.findById(id);
    }

    @GetMapping()
    public List<ExposureType> getExposureType(){
        return repo.findAll();
    }

    @PostMapping
    public ExposureType newExposureType(@RequestBody ExposureType exposureType){
        return repo.save(exposureType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExposureType> updateExposureType(@RequestBody ExposureType exposureType, @PathVariable  long id){
        Optional<ExposureType> tmp = repo.findById(id);
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(exposureType));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteExposureType(@PathVariable long id){
        repo.deleteById(id);
    }
}
