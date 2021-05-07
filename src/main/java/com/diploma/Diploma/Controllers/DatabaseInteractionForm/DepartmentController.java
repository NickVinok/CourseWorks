package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.CloudCombustionMode;
import com.diploma.Diploma.DataBase.Model.Coefficients;
import com.diploma.Diploma.DataBase.Model.Department;
import com.diploma.Diploma.DataBase.Repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentRepo repo;

    @GetMapping("/get")
    public Optional<Department> getDepartment(@RequestBody Department id){
        return repo.findById(id.getId());
    }

    @GetMapping()
    public List<Department> getDepartments(){
        return repo.findAll();
    }

    @GetMapping("/getNew")
    public Department getNewDepartment(){
        return new Department();
    }

    @PostMapping("/create")
    public ResponseEntity<Department> newDepartment(@RequestBody Department department){
        Optional<Department> tmp = repo.findById(department.getId());
        if(tmp.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else{
            return ResponseEntity.ok(repo.save(department));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department){
         Optional<Department> tmp = repo.findById(department.getId());
         if(tmp.isPresent()){
             return ResponseEntity.ok(repo.save(department));
         } else{
             return ResponseEntity.status(HttpStatus.CONFLICT).build();
         }
    }

    @PostMapping("/delete")
    public ResponseEntity<Department> deleteCDepartment(@RequestBody Department department){
        Optional<Department> tmp = repo.findById(department.getId());
        if(tmp.isPresent()){
            repo.deleteById(department.getId());
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
