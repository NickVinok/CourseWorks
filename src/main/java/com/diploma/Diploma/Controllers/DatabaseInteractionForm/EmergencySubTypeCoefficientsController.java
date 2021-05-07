package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.CloudCombustionMode;
import com.diploma.Diploma.DataBase.Model.EmergencySubTypeCoefficients;
import com.diploma.Diploma.DataBase.Model.Keys.EmergencySubTypeCoefficientsKey;
import com.diploma.Diploma.DataBase.Repo.EmergencySubTypeCoefficientsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emergencySubTypeCoefficients")
public class EmergencySubTypeCoefficientsController {
    @Autowired
    EmergencySubTypeCoefficientsRepo repo;

    @PostMapping("/get")
    public Optional<EmergencySubTypeCoefficients> getEmergencySubTypeCoefficients(@RequestBody EmergencySubTypeCoefficients emergencySubTypeCoefficients){
        return repo.findById(emergencySubTypeCoefficients.getEmergencySubTypeCoefficientsKey());
    }

    @GetMapping()
    public List<EmergencySubTypeCoefficients> getEmergencySubTypeCoefficients(){
        return repo.findAll();
    }

    @GetMapping("/getNew")
    public EmergencySubTypeCoefficients getNewEmergencySubTypeCoefficients(){
        return new EmergencySubTypeCoefficients();
    }

    @PostMapping("/create")
    public ResponseEntity<EmergencySubTypeCoefficients> newEmergencySubTypeCoefficients(@RequestBody EmergencySubTypeCoefficients emergencySubTypeCoefficients){
        //System.out.println(emergencySubTypeCoefficients);
        var obj = repo.findById(emergencySubTypeCoefficients.getEmergencySubTypeCoefficientsKey());
        if(obj.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(obj.get());
        } else{
            return ResponseEntity.ok(repo.save(emergencySubTypeCoefficients));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<EmergencySubTypeCoefficients> updateEmergencySubTypeCoefficients(@RequestBody EmergencySubTypeCoefficients emergency){
        Optional<EmergencySubTypeCoefficients> tmp = repo.findById(emergency.getEmergencySubTypeCoefficientsKey());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(emergency));
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<EmergencySubTypeCoefficients> deleteEmergencySubTypeCoefficients(@RequestBody EmergencySubTypeCoefficients emergency){
        Optional<EmergencySubTypeCoefficients> tmp = repo.findById(emergency.getEmergencySubTypeCoefficientsKey());
        if(tmp.isPresent()) {
            repo.deleteById(emergency.getEmergencySubTypeCoefficientsKey());
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
