package Controllers.DatabaseInteractionForm;

import DataBase.Model.Enterprise;
import DataBase.Model.EquipmentType;
import DataBase.Repo.EquipmentTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/EquipmentType")
public class EquipmentTypeController {
    @Autowired
    EquipmentTypeRepo repo;

    @GetMapping("/{id}")
    public EquipmentType getEquipmentType(@PathVariable long id){
        return repo.findById(id).get();
    }

    @GetMapping()
    public List<EquipmentType> getEquipmentType(){
        return repo.findAll();
    }

    @PostMapping
    public EquipmentType newEquipmentType(@RequestBody EquipmentType equipmentType){
        return repo.save(equipmentType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentType> updateEquipmentType(@RequestBody EquipmentType equipmentType, @PathVariable  long id){
        Optional<EquipmentType> tmp = repo.findById(id);
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(equipmentType));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteEquipmentType(@PathVariable long id){
        repo.deleteById(id);
    }
}
