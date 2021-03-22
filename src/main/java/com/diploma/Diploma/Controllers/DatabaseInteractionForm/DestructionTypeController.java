package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.Department;
import com.diploma.Diploma.DataBase.Model.DestructionType;
import com.diploma.Diploma.DataBase.Repo.DestructionTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/destructionType")
public class DestructionTypeController {
    @Autowired
    DestructionTypeRepo repo;

    @GetMapping("/{id}")
    public Optional<DestructionType> getDestructionType(@PathVariable long id){
        return repo.findById(id);
    }

    @GetMapping()
    public List<DestructionType> getDestructionType(){
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<DestructionType> newDestructionType(@RequestBody DestructionType destructionType){
        return ResponseEntity.ok(repo.save(destructionType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DestructionType> updateDestructionType(@RequestBody DestructionType destructionType, @PathVariable  long id){
        Optional<DestructionType> tmp = repo.findById(id);
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(destructionType));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteDestructionType(@PathVariable long id){
        repo.deleteById(id);
    }
}
