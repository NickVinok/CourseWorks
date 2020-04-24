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

    @GetMapping("/{id}")
    public EmergencySubType getEmergencySubType(@PathVariable long id){
        return repo.findById(id).get();
    }

    @GetMapping()
    public List<EmergencySubType> getEmergencies(){
        return repo.findAll();
    }

    @PostMapping
    public EmergencySubType newEmergencySubType(@RequestBody EmergencySubType emergency){
        return repo.save(emergency);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmergencySubType> updateEmergencySubType(@RequestBody EmergencySubType emergency, @PathVariable  long id){
        Optional<EmergencySubType> tmp = repo.findById(id);
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(emergency));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteEmergencySubType(@PathVariable long id){
        repo.deleteById(id);
    }
}
