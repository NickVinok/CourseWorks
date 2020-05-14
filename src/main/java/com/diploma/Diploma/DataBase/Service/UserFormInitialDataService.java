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
        return enterpriseRepo.findAll();
    }
}
