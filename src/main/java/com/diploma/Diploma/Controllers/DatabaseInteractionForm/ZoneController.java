package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.Zone;
import com.diploma.Diploma.DataBase.Repo.ZoneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/zone")
public class ZoneController {
    @Autowired
    ZoneRepo repo;

    @GetMapping("/{id}")
    public Zone getZone(@PathVariable long id){
        return repo.findById(id).get();
    }

    @GetMapping()
    public List<Zone> getZone(){
        return repo.findAll();
    }

    @PostMapping
    public Zone newZone(@RequestBody Zone zone){
        return repo.save(zone);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Zone> updateZone(@RequestBody Zone zone, @PathVariable  long id){
        Optional<Zone> tmp = repo.findById(id);
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(zone));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteZone(@PathVariable long id){
        repo.deleteById(id);
    }
}
