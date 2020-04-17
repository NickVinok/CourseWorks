package Controllers.DatabaseInteractionForm;

import DataBase.Model.EmergencySubType;
import DataBase.Model.Enterprise;
import DataBase.Repo.EnterpriseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {
    @Autowired
    EnterpriseRepo repo;

    @GetMapping("/{id}")
    public Enterprise getEnterprise(@PathVariable long id){
        return repo.findById(id).get();
    }

    @GetMapping()
    public List<Enterprise> getEmergencies(){
        return repo.findAll();
    }

    @PostMapping
    public Enterprise newEnterprise(@RequestBody Enterprise enterprise){
        return repo.save(enterprise);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enterprise> updateEnterprise(@RequestBody Enterprise enterprise, @PathVariable  long id){
        Optional<Enterprise> tmp = repo.findById(id);
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(enterprise));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteEnterprise(@PathVariable long id){
        repo.deleteById(id);
    }
}
