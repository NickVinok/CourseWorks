package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.EmergencySubType;
import com.diploma.Diploma.DataBase.Repo.EmergencySubTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emergencySubType")
public class EmergencySubTypeController {
    @Autowired
    EmergencySubTypeRepo repo;

    @PostMapping("/get")
    public Optional<EmergencySubType> getEmergencySubType(@RequestBody EmergencySubType emergency){
        return repo.findById(emergency.getId());
    }

    @GetMapping()
    public List<EmergencySubType> getEmergencies(){
        return repo.findAll();
    }

    @PostMapping("/create")
    public EmergencySubType newEmergencySubType(@RequestBody EmergencySubType emergency){
        return repo.save(emergency);
    }

    @PostMapping("/update")
    public ResponseEntity<EmergencySubType> updateEmergencySubType(@RequestBody EmergencySubType emergency){
        Optional<EmergencySubType> tmp = repo.findById(emergency.getId());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(emergency));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}")
    public void deleteEmergencySubType(@RequestBody EmergencySubType emergency){
        repo.deleteById(emergency.getId());
    }
}
