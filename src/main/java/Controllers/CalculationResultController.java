package Controllers;

import Controllers.PesponseObjects.CalculationResponse.CalculationResponse;
import Controllers.RequsetObjects.CalculationRequest.CalculationStartRequest;
import DataBase.Model.Calculation;
import DataBase.Model.EmergencySubTypeDamageCalculation;
import DataBase.Repo.EmergencySubTypeDamageCalculationRepo;
import Logics.CalculationLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;

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
