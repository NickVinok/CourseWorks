package Controllers.DatabaseInteractionForm;

import DataBase.Model.Department;
import DataBase.Model.Emergency;
import DataBase.Model.EmergencyScenario;
import DataBase.Model.Keys.EmergencyScenarioKey;
import DataBase.Repo.EmergencyRepo;
import DataBase.Repo.EmergencyScenarioRepo;
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

    @PostMapping("/{id}")
    public EmergencyScenario getEmergencyScenario(@RequestBody EmergencyScenarioKey key){
        return repo.findById(key).get();
    }

    @GetMapping()
    public List<EmergencyScenario> getEmergencyScenarios(){
        return repo.findAll();
    }

    @PostMapping
    public EmergencyScenario newEmergencyScenario(@RequestBody EmergencyScenario emergencyScenario){
        return repo.save(emergencyScenario);
    }

    @PutMapping("/update")
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
