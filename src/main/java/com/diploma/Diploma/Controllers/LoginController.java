package com.diploma.Diploma.Controllers;

import com.diploma.Diploma.Controllers.PesponseObjects.LoginResponses.*;
import com.diploma.Diploma.Controllers.RequsetObjects.LoginRequest;
import com.diploma.Diploma.Logics.LoginLogic;
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
            incorrectLoginResponse.setLogin(loginRequest.getLogin());
            response = incorrectLoginResponse;
        }
        else if(!this.loginLogic.getLoginResponse().isPasswordCorrect()){
            LoginErrorResponse incorrectPasswordResponse = new LoginErrorResponse();
            incorrectPasswordResponse.setLoginResponse(this.loginLogic.getLoginResponse());
            incorrectPasswordResponse.setMessage("Неверный пароль");
            incorrectPasswordResponse.setLogin(loginRequest.getLogin());
            response = incorrectPasswordResponse;
        }

        else if(this.loginLogic.getLoginResponse().getUser().getRole().getName().equals("Проектировщик") ||
                this.loginLogic.getLoginResponse().getUser().getRole().getName().equals("Designer")) {
            LoginResearcherResponse researcherResponse = new LoginResearcherResponse();
            researcherResponse.setLoginResponse(this.loginLogic.getLoginResponse());
            researcherResponse.setInitialDataForCalculation(this.loginLogic.getDesignerFormData());
            response = researcherResponse;
        }
        else if(!this.loginLogic.getLoginResponse().getUser().getRole().getName().contains("Проектировщик") &&
                !this.loginLogic.getLoginResponse().getUser().getRole().getName().contains("Designer")){
            LoginAdminResponse adminResponse = new LoginAdminResponse();
            adminResponse.setLoginResponse(this.loginLogic.getLoginResponse());
            adminResponse.setAdminFormData(this.loginLogic.getAdminFormData());
            response = adminResponse;
        }
        else {
            response = new LoginErrorResponse();
        }

        return response;
    }
}
