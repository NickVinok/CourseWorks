package com.diploma.Diploma.DataBase.Service;

import com.diploma.Diploma.DataBase.Model.*;
import com.diploma.Diploma.DataBase.Repo.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class UserFormInitialDataService {
    @Autowired
    private EnterpriseRepo enterpriseRepo;
    @Autowired
    private EquipmentInDepartmentRepo equipmentInDepartmentRepo;

    public List<Enterprise> getDataForCalculation(){
        List<Enterprise> enterprises = enterpriseRepo.findAll();
        //Находим все доступные предприятия
        for(Enterprise e : enterprises){
            for(Department d : e.getDepartments()){
                var tmpEiD = equipmentInDepartmentRepo.findByDepartmentIdAndSubstanceIdNot(d.getId(), -1);
                tmpEiD.ifPresent(d::setEquipmentInDepartments);
            }
        }
        return enterprises;
    }
}
