package com.diploma.Diploma.Controllers.PesponseObjects.CalculationResponse;

import com.diploma.Diploma.DataBase.Model.EmergencySubType;
import com.diploma.Diploma.DataBase.Model.EmergencySubTypeDamageCalculation;
import com.diploma.Diploma.Logics.CalculationResults;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;

@Data
public class CalculationResponse {
    private HashMap<String, ArrayList<CalculationResults>> emergencies;
}
