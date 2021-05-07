package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.CloudCombustionMode;
import com.diploma.Diploma.DataBase.Model.EquipmentType;
import com.diploma.Diploma.DataBase.Repo.EquipmentTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<EquipmentType> newEquipmentType(@RequestBody EquipmentType equipmentType){
        Optional<EquipmentType> tmp = repo.findById(equipmentType.getId());
        if(tmp.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else{
            return ResponseEntity.ok(repo.save(equipmentType));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<EquipmentType> updateEquipmentType(@RequestBody EquipmentType equipmentType){
        Optional<EquipmentType> tmp = repo.findById(equipmentType.getId());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(equipmentType));
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<EquipmentType> deleteEquipmentType(@RequestBody EquipmentType equipmentType){
        Optional<EquipmentType> tmp = repo.findById(equipmentType.getId());
        if(tmp.isPresent()){
            repo.deleteById(equipmentType.getId());
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }
}
