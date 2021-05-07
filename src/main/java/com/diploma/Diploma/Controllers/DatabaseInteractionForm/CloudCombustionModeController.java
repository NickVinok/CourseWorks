package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.CloudCombustionMode;
import com.diploma.Diploma.DataBase.Model.Department;
import com.diploma.Diploma.DataBase.Model.Keys.CloudCombustionModeKey;
import com.diploma.Diploma.DataBase.Repo.CloudCombustionModeRepo;
import com.diploma.Diploma.DataBase.Repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Optional<CloudCombustionMode> getCloudCombustionMode(@RequestBody CloudCombustionMode ccm) {
        return repo.findById(ccm.getCloudCombustionModeKey());
    }

    @GetMapping()
    public List<CloudCombustionMode> getCloudCombustionModes(){
        return repo.findAll();
    }

    @GetMapping("/getNew")
    public CloudCombustionMode getNewCloudCombustionMode(){
        return new CloudCombustionMode();
    }

    @PostMapping("/create")
    public ResponseEntity<CloudCombustionMode> newCloudCombustionMode(@RequestBody CloudCombustionMode ccm){
        Optional<CloudCombustionMode> tmp = repo.findById(ccm.getCloudCombustionModeKey());
        if(tmp.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            return ResponseEntity.ok(repo.save(ccm));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<CloudCombustionMode> updateCloudCombustionMode(@RequestBody CloudCombustionMode cloudCombustionMode){
        Optional<CloudCombustionMode> tmp = repo.findById(cloudCombustionMode.getCloudCombustionModeKey());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(cloudCombustionMode));
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<CloudCombustionMode> deleteCloudCombustionMode(@RequestBody CloudCombustionMode ccm){
        Optional<CloudCombustionMode> tmp = repo.findById(ccm.getCloudCombustionModeKey());
        if(tmp.isPresent()) {
            repo.deleteById(ccm.getCloudCombustionModeKey());
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
