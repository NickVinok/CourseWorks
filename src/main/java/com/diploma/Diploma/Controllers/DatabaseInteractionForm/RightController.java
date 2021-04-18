package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.CloudCombustionMode;
import com.diploma.Diploma.DataBase.Model.Rights;
import com.diploma.Diploma.DataBase.Repo.RightRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/right")
public class RightController {
    @Autowired
    RightRepo repo;

    @PostMapping("/get")
    public Optional<Rights> getRight(@RequestBody Rights rights){
        return repo.findById(rights.getId());
    }

    @GetMapping()
    public List<Rights> getRight(){
        return repo.findAll();
    }

    @GetMapping("/getNew")
    public Rights getNewRights(){
        return new Rights();
    }

    @PostMapping("/create")
    public Rights newRight(@RequestBody Rights rights){
        return repo.save(rights);
    }

    @PostMapping("/update")
    public ResponseEntity<Rights> updateRight(@RequestBody Rights rights){
        Optional<Rights> tmp = repo.findById(rights.getId());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(rights));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/delete")
    public void deleteRight(@RequestBody Rights rights){
        repo.deleteById(rights.getId());
    }
}
