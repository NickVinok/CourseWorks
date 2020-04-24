package com.diploma.Diploma.DataBase.Service;

import com.diploma.Diploma.DataBase.Model.*;
import com.diploma.Diploma.DataBase.Model.Keys.CloudCombustionModeKey;
import com.diploma.Diploma.DataBase.Repo.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class CloudCombustionService {
    @Autowired
    private CloudCombustionModeRepo cloudCombustionModeRepo;

    public CloudCombustionMode getFlameFrontSpeed(Substance substance, Department department){
        CloudCombustionModeKey key = new CloudCombustionModeKey(department.getClutterClass().getId(),
                substance.getExplosionSensitivity().getId());
        return cloudCombustionModeRepo.findById(key).get();
    }
}
