package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.CloudCombustionMode;
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

    @GetMapping("/get")
    public Optional<DestructionType> getDestructionType(@RequestBody DestructionType destructionType){
        return repo.findById(destructionType.getId());
    }

    @GetMapping()
    public List<DestructionType> getDestructionType(){
        return repo.findAll();
    }

    @GetMapping("/getNew")
    public DestructionType getNewDestructionType(){
        return new DestructionType();
    }

    @PostMapping("/create")
    public ResponseEntity<DestructionType> newDestructionType(@RequestBody DestructionType destructionType){
        return ResponseEntity.ok(repo.save(destructionType));
    }

    @PostMapping("/update")
    public ResponseEntity<DestructionType> updateDestructionType(@RequestBody DestructionType destructionType){
        Optional<DestructionType> tmp = repo.findById(destructionType.getId());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(destructionType));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/delete")
    public void deleteDestructionType(@RequestBody DestructionType destructionType){
        repo.deleteById(destructionType.getId());
    }
}
