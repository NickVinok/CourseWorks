package com.diploma.Diploma.Controllers;

import com.diploma.Diploma.DataBase.Model.Department;
import com.diploma.Diploma.DataBase.Model.Enterprise;
import com.diploma.Diploma.DataBase.Model.EquipmentInDepartment;
import com.diploma.Diploma.DataBase.Model.PotentiallyDangerousSituation;
import com.diploma.Diploma.DataBase.Repo.DepartmentRepo;
import com.diploma.Diploma.DataBase.Repo.EquipmentInDepartmentRepo;
import com.diploma.Diploma.DataBase.Repo.PotentiallyDangerousSituationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/prepare")
public class CalculationPreparationController {
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private EquipmentInDepartmentRepo equipmentInDepartmentRepo;
    @Autowired
    private PotentiallyDangerousSituationRepo potentiallyDangerousSituationRepo;

    @PostMapping("/departments")
    public ResponseEntity<List<EquipmentInDepartment>> postWorkingDepartments(@RequestBody Enterprise enterprise){
        var tmp = departmentRepo.findByEnterpriseId(enterprise.getId());
        if( tmp.isEmpty() || tmp.get().isEmpty()){
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
        else {
            List<Department> departmentList= tmp.get();
            var tmp2 = equipmentInDepartmentRepo.findBySubstanceIdIsNotNullAndDepartmentIdIn(
                    departmentList.stream()
                            .map(Department::getId)
                            .collect(Collectors.toList())
            );
            if (tmp2.isEmpty() || tmp2.get().isEmpty()){
                return ResponseEntity.badRequest().body(Collections.emptyList());
            } else{
                return ResponseEntity.ok().body(tmp2.get());
            }
        }
    }

    @PostMapping("/potentiallyDangerousSituation")
    public ResponseEntity<List<PotentiallyDangerousSituation>> postPossibleIncidents(
            @RequestBody EquipmentInDepartment equipmentInDepartment){
        return ResponseEntity.ok().body(potentiallyDangerousSituationRepo.findByEquipmentTypeId(
                equipmentInDepartment.getEquipmentClass().getEquipmentType().getId()));
    }
}
