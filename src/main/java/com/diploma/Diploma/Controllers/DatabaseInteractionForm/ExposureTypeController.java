package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.CloudCombustionMode;
import com.diploma.Diploma.DataBase.Model.ExposureType;
import com.diploma.Diploma.DataBase.Repo.ExposureTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exposureType")
public class ExposureTypeController {
    @Autowired
    ExposureTypeRepo repo;

    @PostMapping("/get")
    public Optional<ExposureType> getExposureType(@RequestBody ExposureType exposureType){
        return repo.findById(exposureType.getId());
    }

    @GetMapping()
    public List<ExposureType> getExposureType(){
        return repo.findAll();
    }

    @GetMapping("/getNew")
    public ExposureType getNewExposureType(){
        return new ExposureType();
    }

    @PostMapping("/create")
    public ResponseEntity<ExposureType> newExposureType(@RequestBody ExposureType exposureType){
        Optional<ExposureType> tmp = repo.findById(exposureType.getId());
        if(tmp.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else{
            return ResponseEntity.ok(repo.save(exposureType));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ExposureType> updateExposureType(@RequestBody ExposureType exposureType){
        Optional<ExposureType> tmp = repo.findById(exposureType.getId());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(exposureType));
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<ExposureType> deleteExposureType(@RequestBody ExposureType exposureType){
        Optional<ExposureType> tmp = repo.findById(exposureType.getId());
        if(tmp.isPresent()){
            repo.deleteById(exposureType.getId());
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
