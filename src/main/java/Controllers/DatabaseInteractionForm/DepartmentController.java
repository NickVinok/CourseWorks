package Controllers.DatabaseInteractionForm;

import DataBase.Model.Coefficients;
import DataBase.Model.Department;
import DataBase.Repo.CoefficientsRepo;
import DataBase.Repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentRepo repo;

    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable long id){
        return repo.findById(id).get();
    }

    @GetMapping()
    public List<Department> getDepartments(){
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<Department> newDepartment(@RequestBody Department department){
        return ResponseEntity.ok(repo.save(department));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department, @PathVariable  long id){
         Optional<Department> tmp = repo.findById(id);
         if(tmp.isPresent()){
             return ResponseEntity.ok(repo.save(department));
         } else{
             return ResponseEntity.notFound().build();
         }
    }

    @DeleteMapping("/{id}")
    public void deleteCDepartment(@PathVariable long id){
        repo.deleteById(id);
    }
}
