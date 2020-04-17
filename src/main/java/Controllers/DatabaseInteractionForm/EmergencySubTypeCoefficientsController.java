package Controllers.DatabaseInteractionForm;

import DataBase.Model.Department;
import DataBase.Model.EmergencyScenario;
import DataBase.Model.EmergencySubTypeCoefficients;
import DataBase.Model.Keys.EmergencyScenarioKey;
import DataBase.Model.Keys.EmergencySubTypeCoefficientsKey;
import DataBase.Repo.EmergencyScenarioRepo;
import DataBase.Repo.EmergencySubTypeCoefficientsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emergencySubTypeCoefficients")
public class EmergencySubTypeCoefficientsController {
    @Autowired
    EmergencySubTypeCoefficientsRepo repo;

    @PostMapping("/{id}")
    public EmergencySubTypeCoefficients getEmergencySubTypeCoefficients(@RequestBody EmergencySubTypeCoefficientsKey key){
        return repo.findById(key).get();
    }

    @GetMapping()
    public List<EmergencySubTypeCoefficients> getEmergencySubTypeCoefficients(){
        return repo.findAll();
    }

    @PostMapping
    public EmergencySubTypeCoefficients newEmergencySubTypeCoefficients(@RequestBody EmergencySubTypeCoefficients emergencySubTypeCoefficients){
        return repo.save(emergencySubTypeCoefficients);
    }

    @PutMapping("/update")
    public ResponseEntity<EmergencySubTypeCoefficients> updateEmergencySubTypeCoefficients(@RequestBody EmergencySubTypeCoefficients emergency){
        Optional<EmergencySubTypeCoefficients> tmp = repo.findById(emergency.getEmergencySubTypeCoefficientsKey());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(emergency));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete")
    public void deleteEmergencySubTypeCoefficients(@RequestBody EmergencySubTypeCoefficientsKey key){
        repo.deleteById(key);
    }
}
