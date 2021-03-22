package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.EquipmentClass;
import com.diploma.Diploma.DataBase.Repo.EquipmentClassRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipmentClass")
public class EquipmentClassController {
    @Autowired
    EquipmentClassRepo repo;

    @GetMapping("/{id}")
    public Optional<EquipmentClass> getEquipmentClass(@PathVariable long id){
        return repo.findById(id);
    }

    @GetMapping()
    public List<EquipmentClass> getEmergencies(){
        return repo.findAll();
    }

    @PostMapping
    public EquipmentClass newEquipmentClass(@RequestBody EquipmentClass equipmentClass){
        return repo.save(equipmentClass);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentClass> updateEquipmentClass(@RequestBody EquipmentClass equipmentClass, @PathVariable  long id){
        Optional<EquipmentClass> tmp = repo.findById(id);
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(equipmentClass));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteEquipmentClass(@PathVariable long id){
        repo.deleteById(id);
    }
}
