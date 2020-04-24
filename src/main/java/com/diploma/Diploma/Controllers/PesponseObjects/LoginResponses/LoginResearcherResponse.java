package com.diploma.Diploma.Controllers.PesponseObjects.LoginResponses;

import com.diploma.Diploma.DataBase.Model.Enterprise;
import com.diploma.Diploma.Utils.LoginResponse;
import lombok.Data;

import java.util.List;

@Data
public class LoginResearcherResponse implements LoginBasicResponse{
    private LoginResponse loginResponse;
    private List<Enterprise> initialDataForCalculation;
}
