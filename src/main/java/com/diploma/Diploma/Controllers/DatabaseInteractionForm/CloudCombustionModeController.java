package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.CloudCombustionMode;
import com.diploma.Diploma.DataBase.Model.Department;
import com.diploma.Diploma.DataBase.Model.Keys.CloudCombustionModeKey;
import com.diploma.Diploma.DataBase.Repo.CloudCombustionModeRepo;
import com.diploma.Diploma.DataBase.Repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cloudCombustion")
public class CloudCombustionModeController {
    @Autowired
    CloudCombustionModeRepo repo;

    @PostMapping("/get")
    public Optional<CloudCombustionMode> getCloudCombustionMode(@RequestBody CloudCombustionModeKey id) {
        return repo.findById(id);
    }

    @GetMapping()
    public List<CloudCombustionMode> getCloudCombustionModes(){
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<CloudCombustionMode> newCloudCombustionMode(@RequestBody CloudCombustionMode department){
        return ResponseEntity.ok(repo.save(department));
    }

    @PostMapping("/update")
    public ResponseEntity<CloudCombustionMode> updateCloudCombustionMode(@RequestBody CloudCombustionMode cloudCombustionMode){
        Optional<CloudCombustionMode> tmp = repo.findById(cloudCombustionMode.getCloudCombustionModeKey());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(cloudCombustionMode));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/delete")
    public void deleteCloudCombustionMode(@RequestBody CloudCombustionModeKey id){
        repo.deleteById(id);
    }
}
