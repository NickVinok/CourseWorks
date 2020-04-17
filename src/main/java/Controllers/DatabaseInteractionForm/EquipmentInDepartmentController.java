package Controllers.DatabaseInteractionForm;

import DataBase.Model.EquipmentInDepartment;
import DataBase.Model.EquipmentType;
import DataBase.Repo.EquipmentInDepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipmentInDepartment")
public class EquipmentInDepartmentController {
    @Autowired
    EquipmentInDepartmentRepo repo;

    @GetMapping("/{id}")
    public EquipmentInDepartment getEquipmentInDepartment(@PathVariable long id){
        return repo.findById(id).get();
    }

    @GetMapping()
    public List<EquipmentInDepartment> getEquipmentInDepartment(){
        return repo.findAll();
    }

    @PostMapping
    public EquipmentInDepartment newEquipmentInDepartment(@RequestBody EquipmentInDepartment equipmentInDepartment){
        return repo.save(equipmentInDepartment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentInDepartment> updateEquipmentInDepartment(@RequestBody EquipmentInDepartment equipmentInDepartment, @PathVariable  long id){
        Optional<EquipmentInDepartment> tmp = repo.findById(id);
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(equipmentInDepartment));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteEquipmentInDepartment(@PathVariable long id){
        repo.deleteById(id);
    }
}
