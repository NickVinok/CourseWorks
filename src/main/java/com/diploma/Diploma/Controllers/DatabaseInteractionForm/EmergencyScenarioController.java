package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.EmergencyScenario;
import com.diploma.Diploma.DataBase.Model.Keys.EmergencyScenarioKey;
import com.diploma.Diploma.DataBase.Repo.EmergencyScenarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emergencyScenario")
public class EmergencyScenarioController {
    @Autowired
    EmergencyScenarioRepo repo;

    @PostMapping("/get")
    public Optional<EmergencyScenario> getEmergencyScenario(@RequestBody EmergencyScenarioKey key){
        //System.out.println(key);
        return repo.findById(key);
    }

    @GetMapping()
    public List<EmergencyScenario> getEmergencyScenarios(){
        return repo.findAll();
    }

    @PostMapping("/create")
    public EmergencyScenario newEmergencyScenario(@RequestBody EmergencyScenario emergencyScenario){
        return repo.save(emergencyScenario);
    }

    @PostMapping("/update")
    public ResponseEntity<EmergencyScenario> updateEmergencyScenario(@RequestBody EmergencyScenario emergency){
        Optional<EmergencyScenario> tmp = repo.findById(emergency.getEmergencyScenarioKey());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(emergency));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/delete")
    public void deleteEmergencyScenario(@RequestBody EmergencyScenarioKey key){
        repo.deleteById(key);
    }
}
