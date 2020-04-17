package Controllers.PesponseObjects.LoginResponses;

import DataBase.Model.Enterprise;
import Utils.LoginResponse;
import lombok.Data;

import java.util.List;

@Data
public class LoginResearcherResponse implements LoginBasicResponse{
    private LoginResponse loginResponse;
    private List<Enterprise> initialDataForCalculation;
}
