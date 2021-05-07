package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.CalculationVariableParameters;
import com.diploma.Diploma.DataBase.Repo.CalculationVariableParametersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/calculationVariableParameters")
public class CalculationVariableParametersController {
    @Autowired
    CalculationVariableParametersRepo repo;

    @PostMapping("/get")
    public Optional<CalculationVariableParameters> getCalculationVariableParameter(@RequestBody CalculationVariableParameters cvp){
        //System.out.println(key);
        return repo.findById(cvp.getId());
    }

    @GetMapping()
    public List<CalculationVariableParameters> getCalculationVariableParameters(){
        return repo.findAll();
    }

    @GetMapping("/getNew")
    public CalculationVariableParameters getNewCalculationVariableParameter(){
        return new CalculationVariableParameters();
    }

    @PostMapping("/create")
    public ResponseEntity<CalculationVariableParameters> newCalculationVariableParameter(@RequestBody CalculationVariableParameters cvp){
        Optional<CalculationVariableParameters> tmp = repo.findById(cvp.getId());
        if(tmp.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else{
            return ResponseEntity.ok(repo.save(cvp));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<CalculationVariableParameters> updateCalculationVariableParameter(@RequestBody CalculationVariableParameters cvp){
        Optional<CalculationVariableParameters> tmp = repo.findById(cvp.getId());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(cvp));
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<CalculationVariableParameters> deleteCalculationVariableParameters(@RequestBody CalculationVariableParameters cvp){
        Optional<CalculationVariableParameters> tmp = repo.findById(cvp.getId());
        if(tmp.isPresent()){
            repo.deleteById(cvp.getId());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
