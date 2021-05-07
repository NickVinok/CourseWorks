package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.CloudCombustionMode;
import com.diploma.Diploma.DataBase.Model.EquipmentInDepartment;
import com.diploma.Diploma.DataBase.Repo.EquipmentInDepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipmentInDepartment")
public class EquipmentInDepartmentController {
    @Autowired
    EquipmentInDepartmentRepo repo;

    @PostMapping("/get")
    public Optional<EquipmentInDepartment> getEquipmentInDepartment(@RequestBody EquipmentInDepartment equipmentInDepartment){
        return repo.findById(equipmentInDepartment.getId());
    }

    @GetMapping()
    public List<EquipmentInDepartment> getEquipmentInDepartment(){
        return repo.findAll();
    }

    @GetMapping("/getNew")
    public EquipmentInDepartment getNewEquipmentInDepartment(){
        return new EquipmentInDepartment();
    }

    @PostMapping("/create")
    public ResponseEntity<EquipmentInDepartment> newEquipmentInDepartment(@RequestBody EquipmentInDepartment equipmentInDepartment){
        Optional<EquipmentInDepartment> tmp = repo.findById(equipmentInDepartment.getId());
        if(tmp.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else{
            return ResponseEntity.ok(repo.save(equipmentInDepartment));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<EquipmentInDepartment> updateEquipmentInDepartment(@RequestBody EquipmentInDepartment equipmentInDepartment){
        Optional<EquipmentInDepartment> tmp = repo.findById(equipmentInDepartment.getId());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(equipmentInDepartment));
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<EquipmentInDepartment> deleteEquipmentInDepartment(@RequestBody EquipmentInDepartment equipmentInDepartment){
        Optional<EquipmentInDepartment> tmp = repo.findById(equipmentInDepartment.getId());
        if(tmp.isPresent()){
            repo.deleteById(equipmentInDepartment.getId());
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
