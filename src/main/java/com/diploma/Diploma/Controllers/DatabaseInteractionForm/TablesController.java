package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.CloudCombustionMode;
import com.diploma.Diploma.DataBase.Model.Keys.CloudCombustionModeKey;
import com.diploma.Diploma.DataBase.Model.Tables;
import com.diploma.Diploma.DataBase.Repo.TablesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tables")
public class TablesController {
    @Autowired
    TablesRepo repo;

    @PostMapping("/get")
    public Optional<Tables> getTable(@RequestBody Tables t) {
        return repo.findById(t.getId());
    }

    @GetMapping()
    public List<Tables> getTables(){
        return repo.findAll();
    }

    @GetMapping("/getNew")
    public Tables getNewTable(){
        return new Tables();
    }

    @PostMapping("/create")
    public ResponseEntity<Tables> newTable(@RequestBody Tables t){
        Optional<Tables> tmp = repo.findById(t.getId());
        if(tmp.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else{
            return ResponseEntity.ok(repo.save(t));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Tables> updateTable(@RequestBody Tables t){
        Optional<Tables> tmp = repo.findById(t.getId());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(t));
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<Tables> deleteTable(@RequestBody Tables t){
        Optional<Tables> tmp = repo.findById(t.getId());
        if(tmp.isPresent()){
            repo.deleteById(t.getId());
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
