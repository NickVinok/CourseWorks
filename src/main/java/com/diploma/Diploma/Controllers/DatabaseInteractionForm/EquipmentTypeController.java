package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.CloudCombustionMode;
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

    @PostMapping("/get")
    public Optional<EquipmentType> getEquipmentType(@RequestBody EquipmentType equipmentType){
        return repo.findById(equipmentType.getId());
    }

    @GetMapping()
    public List<EquipmentType> getEquipmentType(){
        return repo.findAll();
    }

    @GetMapping("/getNew")
    public EquipmentType getNewEquipmentType(){
        return new EquipmentType();
    }

    @PostMapping("/create")
    public EquipmentType newEquipmentType(@RequestBody EquipmentType equipmentType){
        return repo.save(equipmentType);
    }

    @PostMapping("/update")
    public ResponseEntity<EquipmentType> updateEquipmentType(@RequestBody EquipmentType equipmentType){
        Optional<EquipmentType> tmp = repo.findById(equipmentType.getId());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(equipmentType));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/delete")
    public void deleteEquipmentType(@RequestBody EquipmentType equipmentType){
        repo.deleteById(equipmentType.getId());
    }
}
