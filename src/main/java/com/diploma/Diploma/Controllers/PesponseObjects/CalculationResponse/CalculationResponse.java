package com.diploma.Diploma.Controllers.PesponseObjects.CalculationResponse;

import com.diploma.Diploma.DataBase.Model.EmergencySubTypeDamageCalculation;
import lombok.Data;

import java.util.ArrayList;

@Data
public class CalculationResponse {
    private ArrayList<EmergencySubTypeDamageCalculation> emergencies;
}
