package com.diploma.Diploma.Controllers.RequsetObjects.CalculationRequest;

import com.diploma.Diploma.DataBase.Model.*;
import com.diploma.Diploma.Utils.CalculationVariableParameters;
import lombok.Data;

@Data
public class CalculationStartRequest {
    private String login;
    private Enterprise enterprise;
    private EquipmentInDepartment equipmentInDepartment;
    private Event event;
    private DestructionType destructionType;
    private CalculationVariableParameters calculationVariableParameters;
}
