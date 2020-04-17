package Controllers.DatabaseInteractionForm;

import DataBase.Model.PotentiallyDangerousSituation;
import DataBase.Model.Right;
import DataBase.Repo.RightRepo;
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
    public Right getRight(@PathVariable long id){
        return repo.findById(id).get();
    }

    @GetMapping()
    public List<Right> getRight(){
        return repo.findAll();
    }

    @PostMapping
    public Right newRight(@RequestBody Right right){
        return repo.save(right);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Right> updateRight(@RequestBody Right right, @PathVariable  long id){
        Optional<DataBase.Model.Right> tmp = repo.findById(id);
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(right));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteRight(@PathVariable long id){
        repo.deleteById(id);
    }
}
