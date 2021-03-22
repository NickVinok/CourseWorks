package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.EquipmentType;
import com.diploma.Diploma.DataBase.Repo.EquipmentTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipmentType")
public class EquipmentTypeController {
    @Autowired
    EquipmentTypeRepo repo;

    @GetMapping("/{id}")
    public Optional<EquipmentType> getEquipmentType(@PathVariable long id){
        return repo.findById(id);
    }

    @GetMapping()
    public List<EquipmentType> getEquipmentType(){
        return repo.findAll();
    }

    @PostMapping
    public EquipmentType newEquipmentType(@RequestBody EquipmentType equipmentType){
        return repo.save(equipmentType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentType> updateEquipmentType(@RequestBody EquipmentType equipmentType, @PathVariable  long id){
        Optional<EquipmentType> tmp = repo.findById(id);
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(equipmentType));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteEquipmentType(@PathVariable long id){
        repo.deleteById(id);
    }
}
