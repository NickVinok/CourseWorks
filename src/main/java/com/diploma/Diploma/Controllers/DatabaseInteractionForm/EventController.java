package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.Event;
import com.diploma.Diploma.DataBase.Repo.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    EventRepo repo;

    @GetMapping("/{id}")
    public Event getEvent(@PathVariable long id){
        return repo.findById(id).get();
    }

    @GetMapping()
    public List<Event> getEvent(){
        return repo.findAll();
    }

    @PostMapping
    public Event newEvent(@RequestBody Event event){
        return repo.save(event);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@RequestBody Event event, @PathVariable  long id){
        Optional<Event> tmp = repo.findById(id);
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(event));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable long id){
        repo.deleteById(id);
    }
}
