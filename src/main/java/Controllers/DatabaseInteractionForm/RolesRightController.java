package Controllers.DatabaseInteractionForm;

import DataBase.Model.Keys.RolesRightKey;
import DataBase.Model.Role;
import DataBase.Model.RolesRight;
import DataBase.Repo.RolesRightRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rolesRight")
public class RolesRightController {
    @Autowired
    RolesRightRepo repo;

    @PostMapping("/{id}")
    public RolesRight getRolesRight(@RequestBody RolesRightKey key){
        return repo.findById(key).get();
    }

    @GetMapping()
    public List<RolesRight> getRolesRight(){
        return repo.findAll();
    }

    @PostMapping
    public RolesRight newRolesRight(@RequestBody RolesRight rolesRight){
        return repo.save(rolesRight);
    }

    @PutMapping("/update")
    public ResponseEntity<RolesRight> updateRolesRight(@RequestBody RolesRight rolesRight){
        Optional<DataBase.Model.RolesRight> tmp = repo.findById(rolesRight.getRolesRightKey());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(rolesRight));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/delete")
    public void deleteRolesRight(@RequestBody RolesRightKey key){
        repo.deleteById(key);
    }
}
