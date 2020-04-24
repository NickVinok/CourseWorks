package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

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

    @GetMapping("/{id}")
    public Rights getRight(@PathVariable long id){
        return repo.findById(id).get();
    }

    @GetMapping()
    public List<Rights> getRight(){
        return repo.findAll();
    }

    @PostMapping
    public Rights newRight(@RequestBody Rights rights){
        return repo.save(rights);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rights> updateRight(@RequestBody Rights rights, @PathVariable  long id){
        Optional<Rights> tmp = repo.findById(id);
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(rights));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteRight(@PathVariable long id){
        repo.deleteById(id);
    }
}
