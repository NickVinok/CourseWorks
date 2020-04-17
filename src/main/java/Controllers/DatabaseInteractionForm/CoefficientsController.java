package Controllers.DatabaseInteractionForm;

import DataBase.Model.Coefficients;
import DataBase.Model.Department;
import DataBase.Repo.CoefficientsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coefficients")
public class CoefficientsController {
    @Autowired
    CoefficientsRepo repo;

    @GetMapping("/{id}")
    public Coefficients getCoefficient(@PathVariable long id){
        return repo.findById(id).get();
    }

    @GetMapping()
    public List<Coefficients> getCoefficients(){
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<Coefficients> newCoefficient(@RequestBody Coefficients coefficients){
        return ResponseEntity.ok(repo.save(coefficients));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coefficients> updateCoefficient(@RequestBody Coefficients coefficients, @PathVariable  long id){
        Optional<Coefficients> tmp = repo.findById(id);
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(coefficients));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}")
    public void deleteCoefficient(@PathVariable long id){
        repo.deleteById(id);
    }
}
