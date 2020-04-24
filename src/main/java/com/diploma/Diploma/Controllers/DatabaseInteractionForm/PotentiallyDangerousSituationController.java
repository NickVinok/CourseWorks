package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.PotentiallyDangerousSituation;
import com.diploma.Diploma.DataBase.Repo.PotentiallyDangerousSituationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/potentiallyDangerousSituation")
public class PotentiallyDangerousSituationController {
    @Autowired
    PotentiallyDangerousSituationRepo repo;

    @GetMapping("/{id}")
    public PotentiallyDangerousSituation getPotentiallyDangerousSituation(@PathVariable long id){
        return repo.findById(id).get();
    }

    @GetMapping()
    public List<PotentiallyDangerousSituation> getPotentiallyDangerousSituation(){
        return repo.findAll();
    }

    @PostMapping
    public PotentiallyDangerousSituation newPotentiallyDangerousSituation(@RequestBody PotentiallyDangerousSituation potentiallyDangerousSituation){
        return repo.save(potentiallyDangerousSituation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PotentiallyDangerousSituation> updatePotentiallyDangerousSituation(@RequestBody PotentiallyDangerousSituation potentiallyDangerousSituation,
                                                                             @PathVariable  long id){
        Optional<PotentiallyDangerousSituation> tmp = repo.findById(id);
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(potentiallyDangerousSituation));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deletePotentiallyDangerousSituation(@PathVariable long id){
        repo.deleteById(id);
    }
}
