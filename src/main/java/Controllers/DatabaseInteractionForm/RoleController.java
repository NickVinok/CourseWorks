package Controllers.DatabaseInteractionForm;

import DataBase.Model.Role;
import DataBase.Repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleRepo repo;

    @GetMapping("/{id}")
    public Role getRole(@PathVariable long id){
        return repo.findById(id).get();
    }

    @GetMapping()
    public List<Role> getRole(){
        return repo.findAll();
    }

    @PostMapping
    public Role newRole(@RequestBody Role role){
        return repo.save(role);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@RequestBody Role role, @PathVariable  long id){
        Optional<Role> tmp = repo.findById(id);
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(role));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable long id){
        repo.deleteById(id);
    }
}
