package Controllers.DatabaseInteractionForm;

import DataBase.Model.ExposureType;
import DataBase.Model.GeneralCoefficients;
import DataBase.Repo.GeneralCoefficientsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/generalCoefficients")
public class GeneralCoefficientsController {
    @Autowired
    GeneralCoefficientsRepo repo;

    @GetMapping("/{id}")
    public GeneralCoefficients getGeneralCoefficient(@PathVariable long id){
        return repo.findById(id).get();
    }

    @GetMapping()
    public List<GeneralCoefficients> getGeneralCoefficients(){
        return repo.findAll();
    }

    @PostMapping
    public GeneralCoefficients newGeneralCoefficients(@RequestBody GeneralCoefficients generalCoefficients){
        return repo.save(generalCoefficients);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralCoefficients> updateGeneralCoefficients(@RequestBody GeneralCoefficients generalCoefficients, @PathVariable  long id){
        Optional<GeneralCoefficients> tmp = repo.findById(id);
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(generalCoefficients));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteGeneralCoefficients(@PathVariable long id){
        repo.deleteById(id);
    }
}
