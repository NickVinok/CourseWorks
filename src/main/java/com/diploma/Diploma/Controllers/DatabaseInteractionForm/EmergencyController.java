package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.Emergency;
import com.diploma.Diploma.DataBase.Repo.EmergencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emergency")
public class EmergencyController {
    @Autowired
    EmergencyRepo repo;

    @GetMapping("/{id}")
    public Emergency getEmergency(@PathVariable long id){
        return repo.findById(id).get();
    }

    @GetMapping()
    public List<Emergency> getEmergencies(){
        return repo.findAll();
    }

    @PostMapping
    public Emergency newEmergency(@RequestBody Emergency emergency){
        return repo.save(emergency);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emergency> updateEmergency(@RequestBody Emergency emergency, @PathVariable  long id){
        Optional<Emergency> tmp = repo.findById(id);
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(emergency));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteEmergency(@PathVariable long id){
        repo.deleteById(id);
    }
}

