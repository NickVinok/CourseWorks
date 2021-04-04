package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.Role;
import com.diploma.Diploma.DataBase.Repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleRepo repo;

    @PostMapping("/get")
    public Optional<Role> getRole(@RequestBody Role role){
        return repo.findById(role.getId());
    }

    @GetMapping()
    public List<Role> getRole(){
        return repo.findAll();
    }

    @PostMapping("/create")
    public Role newRole(@RequestBody Role role){
        return repo.save(role);
    }

    @PostMapping("/update")
    public ResponseEntity<Role> updateRole(@RequestBody Role role){
        Optional<Role> tmp = repo.findById(role.getId());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(role));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/delete")
    public void deleteRole(@RequestBody Role role){
        repo.deleteById(role.getId());
    }
}
