package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.CloudCombustionMode;
import com.diploma.Diploma.DataBase.Model.Keys.RolesRightKey;
import com.diploma.Diploma.DataBase.Model.RolesRight;
import com.diploma.Diploma.DataBase.Repo.RolesRightRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rolesRight")
public class RolesRightController {
    @Autowired
    RolesRightRepo repo;

    @PostMapping("/get")
    public Optional<RolesRight> getRolesRight(@RequestBody RolesRight rolesRight){
        return repo.findById(rolesRight.getRolesRightKey());
    }

    @GetMapping()
    public List<RolesRight> getRolesRight(){
        return repo.findAll();
    }

    @GetMapping("/getNew")
    public RolesRight getNewRolesRight(){
        return new RolesRight();
    }

    @PostMapping
    public RolesRight newRolesRight(@RequestBody RolesRight rolesRight){
        System.out.println(rolesRight);
        return repo.save(rolesRight);
    }

    @PostMapping("/update")
    public ResponseEntity<RolesRight> updateRolesRight(@RequestBody RolesRight rolesRight){
        Optional<com.diploma.Diploma.DataBase.Model.RolesRight> tmp = repo.findById(rolesRight.getRolesRightKey());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(rolesRight));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/delete")
    public void deleteRolesRight(@RequestBody RolesRight rolesRight){
        repo.deleteById(rolesRight.getRolesRightKey());
    }
}
