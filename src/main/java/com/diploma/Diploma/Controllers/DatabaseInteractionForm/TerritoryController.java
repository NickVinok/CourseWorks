package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.Territory;
import com.diploma.Diploma.DataBase.Repo.TerritoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/territory")
public class TerritoryController {
    @Autowired
    TerritoryRepo repo;

    @GetMapping("/{id}")
    public Optional<Territory> getTerritory(@PathVariable long id){
        return repo.findById(id);
    }

    @GetMapping()
    public List<Territory> getTerritory(){
        return repo.findAll();
    }

    @PostMapping
    public Territory newTerritory(@RequestBody Territory territory){
        return repo.save(territory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Territory> updateTerritory(@RequestBody Territory territory, @PathVariable  long id){
        Optional<com.diploma.Diploma.DataBase.Model.Territory> tmp = repo.findById(id);
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(territory));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTerritory(@PathVariable long id){
        repo.deleteById(id);
    }
}
