package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.EmergencyScenario;
import com.diploma.Diploma.DataBase.Model.EmergencyScenarioNode;
import com.diploma.Diploma.DataBase.Repo.EmergencyScenarioRepo;
import com.diploma.Diploma.DataBase.Repo.ScenarioNodeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/scenarioNode")
public class ScenarioNodeController {
    @Autowired
    ScenarioNodeRepo repo;

    @PostMapping("/get")
    public Optional<EmergencyScenarioNode> getEmergencyScenarioNode(@RequestBody EmergencyScenarioNode esn){
        //System.out.println(key);
        return repo.findById(esn.getId());
    }

    @GetMapping()
    public List<EmergencyScenarioNode> getEmergencyScenarioNodes(){
        return repo.findAll();
    }

    @GetMapping("/getNew")
    public EmergencyScenarioNode getNewEmergencyScenarioNode(){
        return new EmergencyScenarioNode();
    }

    @PostMapping("/create")
    public EmergencyScenarioNode newEmergencyScenarioNode(@RequestBody EmergencyScenarioNode esn){
        return repo.save(esn);
    }

    @PostMapping("/update")
    public ResponseEntity<EmergencyScenarioNode> updateEmergencyScenarioNode(@RequestBody EmergencyScenarioNode esn){
        Optional<EmergencyScenarioNode> tmp = repo.findById(esn.getId());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(esn));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/delete")
    public void deleteEmergencyScenarioNode(@RequestBody EmergencyScenarioNode esn){
        repo.deleteById(esn.getId());
    }
}
