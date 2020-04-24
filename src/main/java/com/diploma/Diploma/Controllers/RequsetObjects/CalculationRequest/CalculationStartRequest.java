package com.diploma.Diploma.Controllers.RequsetObjects.CalculationRequest;

import com.diploma.Diploma.DataBase.Model.*;
import com.diploma.Diploma.Utils.CalculationVariableParameters;
import lombok.Data;

@Data
public class CalculationStartRequest {
    private User user;
    private Enterprise enterprise;
    private Department department;
    private EquipmentInDepartment equipmentInDepartment;
    private Event event;
    private DestructionType destructionType;
    private CalculationVariableParameters calculationVariableParameters;
}
