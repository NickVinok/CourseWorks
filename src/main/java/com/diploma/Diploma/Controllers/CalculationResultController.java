package com.diploma.Diploma.Controllers;

import com.diploma.Diploma.Controllers.PesponseObjects.CalculationResponse.CalculationResponse;
import com.diploma.Diploma.Controllers.RequsetObjects.CalculationRequest.CalculationStartRequest;
import com.diploma.Diploma.DataBase.Repo.EmergencySubTypeDamageCalculationRepo;
import com.diploma.Diploma.Logics.CalculationLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculate")
public class CalculationResultController {
    @Autowired
    CalculationLogic calculationLogic;
    @Autowired
    EmergencySubTypeDamageCalculationRepo emergencySubTypeDamageCalculationRepo;

    @PostMapping
    public CalculationResponse postCalculate(@RequestBody CalculationStartRequest calculationStartRequest){
        calculationLogic.calculate(calculationStartRequest);

        CalculationResponse calculationResponse = new CalculationResponse();
        calculationResponse.setEmergencies(calculationLogic.getResults());
        emergencySubTypeDamageCalculationRepo.saveAll(calculationLogic.getResults());
        return calculationResponse;
    }
}
