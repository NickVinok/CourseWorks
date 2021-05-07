package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.CloudCombustionMode;
import com.diploma.Diploma.DataBase.Model.Event;
import com.diploma.Diploma.DataBase.Repo.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    EventRepo repo;

    @PostMapping("/get")
    public Optional<Event> getEvent(@RequestBody Event event){
        return repo.findById(event.getId());
    }

    @GetMapping()
    public List<Event> getEvent(){
        return repo.findAll();
    }

    @GetMapping("/getNew")
    public Event getNewEvent(){
        return new Event();
    }

    @PostMapping("/create")
    public ResponseEntity<Event> newEvent(@RequestBody Event event){
        Optional<Event> tmp = repo.findById(event.getId());
        if(tmp.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else{
            return ResponseEntity.ok(repo.save(event));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Event> updateEvent(@RequestBody Event event){
        Optional<Event> tmp = repo.findById(event.getId());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(event));
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<Event> deleteEvent(@RequestBody Event event){
        Optional<Event> tmp = repo.findById(event.getId());
        if(tmp.isPresent()){
            repo.deleteById(event.getId());
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
