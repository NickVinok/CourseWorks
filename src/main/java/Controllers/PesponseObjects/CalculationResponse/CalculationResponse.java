package Controllers.PesponseObjects.CalculationResponse;

import DataBase.Model.EmergencySubTypeDamageCalculation;
import lombok.Data;

import java.util.ArrayList;

@Data
public class CalculationResponse {
    private ArrayList<EmergencySubTypeDamageCalculation> emergencies;
}
