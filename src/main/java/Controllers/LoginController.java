package Controllers;

import Controllers.PesponseObjects.LoginResponses.LoginBasicResponse;
import Controllers.PesponseObjects.LoginResponses.LoginErrorResponse;
import Controllers.PesponseObjects.LoginResponses.LoginResearcherResponse;
import Controllers.RequsetObjects.LoginRequest;
import Logics.LoginLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginLogic loginLogic;

    @PostMapping
    public LoginBasicResponse response(@RequestBody LoginRequest loginRequest){
        this.loginLogic.generateResponse(loginRequest.getLogin(), loginRequest.getPassword());
        LoginBasicResponse response;
        if(!this.loginLogic.getLoginResponse().isPresent()){
            LoginErrorResponse incorrectLoginResponse = new LoginErrorResponse();
            incorrectLoginResponse.setLoginResponse(this.loginLogic.getLoginResponse());
            incorrectLoginResponse.setMessage("Пользователя с таким логином не существует");
            response = incorrectLoginResponse;
        }
        else if(!this.loginLogic.getLoginResponse().isPasswordCorrect()){
            LoginErrorResponse incorrectPasswordResponse = new LoginErrorResponse();
            incorrectPasswordResponse.setLoginResponse(this.loginLogic.getLoginResponse());
            incorrectPasswordResponse.setMessage("Неверный пароль");
            response = incorrectPasswordResponse;
        } else if(this.loginLogic.getLoginResponse().isResearcher()){
            LoginResearcherResponse researcherResponse = new LoginResearcherResponse();
            researcherResponse.setLoginResponse(this.loginLogic.getLoginResponse());
            researcherResponse.setInitialDataForCalculation(this.loginLogic.getUserFormData());
            response = researcherResponse;
        } else {
            response = new LoginErrorResponse();
        }

        return response;
    }
}
