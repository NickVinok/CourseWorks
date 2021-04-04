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

    @PostMapping("/get")
    public Optional<Zone> getZone(@RequestBody Zone zone){
        return repo.findById(zone.getId());
    }

    @GetMapping()
    public List<Zone> getZone(){
        return repo.findAll();
    }

    @PostMapping("/upgrade")
    public Zone newZone(@RequestBody Zone zone){
        return repo.save(zone);
    }

    @PostMapping("/update")
    public ResponseEntity<Zone> updateZone(@RequestBody Zone zone){
        Optional<Zone> tmp = repo.findById(zone.getId());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(zone));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/delete")
    public void deleteZone(@RequestBody Zone zone){
        repo.deleteById(zone.getId());
    }
}
