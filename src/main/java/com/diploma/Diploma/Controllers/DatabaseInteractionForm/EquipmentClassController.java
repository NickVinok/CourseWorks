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

    @PostMapping("/get")
    public Optional<EquipmentClass> getEquipmentClass(@RequestBody EquipmentClass equipmentClass){
        return repo.findById(equipmentClass.getId());
    }

    @GetMapping()
    public List<EquipmentClass> getEmergencies(){
        return repo.findAll();
    }

    @PostMapping("/create")
    public EquipmentClass newEquipmentClass(@RequestBody EquipmentClass equipmentClass){
        return repo.save(equipmentClass);
    }

    @PostMapping("/update")
    public ResponseEntity<EquipmentClass> updateEquipmentClass(@RequestBody EquipmentClass equipmentClass){
        Optional<EquipmentClass> tmp = repo.findById(equipmentClass.getId());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(equipmentClass));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/delete")
    public void deleteEquipmentClass(@RequestBody EquipmentClass equipmentClass){
        repo.deleteById(equipmentClass.getId());
    }
}
