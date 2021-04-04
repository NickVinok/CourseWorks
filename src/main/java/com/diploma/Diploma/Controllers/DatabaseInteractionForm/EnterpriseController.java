package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.Enterprise;
import com.diploma.Diploma.DataBase.Repo.EnterpriseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {
    @Autowired
    EnterpriseRepo repo;

    @PostMapping("/get")
    public Optional<Enterprise> getEnterprise(@RequestBody Enterprise enterprise){
        return repo.findById(enterprise.getId());
    }

    @GetMapping()
    public List<Enterprise> getEmergencies(){
        return repo.findAll();
    }

    @PostMapping("/create")
    public Enterprise newEnterprise(@RequestBody Enterprise enterprise){
        return repo.save(enterprise);
    }

    @PostMapping("/update")
    public ResponseEntity<Enterprise> updateEnterprise(@RequestBody Enterprise enterprise){
        Optional<Enterprise> tmp = repo.findById(enterprise.getId());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(enterprise));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/delete")
    public void deleteEnterprise(@RequestBody Enterprise enterprise){
        repo.deleteById(enterprise.getId());
    }
}
