package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.CloudCombustionMode;
import com.diploma.Diploma.DataBase.Model.Territory;
import com.diploma.Diploma.DataBase.Repo.TerritoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/territory")
public class TerritoryController {
    @Autowired
    TerritoryRepo repo;

    @PostMapping("/get")
    public Optional<Territory> getTerritory(@RequestBody Territory territory){
        return repo.findById(territory.getId());
    }

    @GetMapping()
    public List<Territory> getTerritory(){
        return repo.findAll();
    }

    @GetMapping("/getNew")
    public Territory getNewTerritory(){
        return new Territory();
    }

    @PostMapping("/create")
    public ResponseEntity<Territory> newTerritory(@RequestBody Territory territory){

        Optional<Territory> tmp = repo.findById(territory.getId());
        if(tmp.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else{
            return ResponseEntity.ok(repo.save(territory));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Territory> updateTerritory(@RequestBody Territory territory){
        Optional<Territory> tmp = repo.findById(territory.getId());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(territory));
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<Territory> deleteTerritory(@RequestBody Territory territory){
        Optional<Territory> tmp = repo.findById(territory.getId());
        if(tmp.isPresent()){
            repo.deleteById(territory.getId());
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
