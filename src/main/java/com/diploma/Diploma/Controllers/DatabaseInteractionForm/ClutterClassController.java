package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.CloudCombustionMode;
import com.diploma.Diploma.DataBase.Model.ClutterClass;
import com.diploma.Diploma.DataBase.Model.Event;
import com.diploma.Diploma.DataBase.Repo.ClutterClassRepo;
import com.diploma.Diploma.DataBase.Repo.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clutterClass")
public class ClutterClassController {
    @Autowired
    ClutterClassRepo repo;

    @PostMapping("/get")
    public Optional<ClutterClass> getEvent(@RequestBody ClutterClass c){
        return repo.findById(c.getId());
    }

    @GetMapping()
    public List<ClutterClass> getEvent(){
        return repo.findAll();
    }

    @GetMapping("/getNew")
    public ClutterClass getNewClutterClass(){
        return new ClutterClass();
    }

    @PostMapping("/create")
    public ClutterClass newEvent(@RequestBody ClutterClass event){
        return repo.save(event);
    }

    @PostMapping("/update")
    public ResponseEntity<ClutterClass> updateEvent(@RequestBody ClutterClass event){
        Optional<ClutterClass> tmp = repo.findById(event.getId());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(event));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/delete")
    public void deleteEvent(@RequestBody ClutterClass event){
        repo.deleteById(event.getId());
    }
}
