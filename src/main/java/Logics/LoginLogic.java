package Logics;

import DataBase.Model.Enterprise;
import DataBase.Service.LoginService;
import DataBase.Service.UserFormInitialDataService;
import Utils.LoginResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class LoginLogic {
    @Autowired
    private LoginService loginService;

    @Autowired
    private UserFormInitialDataService userFormInitialDataService;

    private LoginResponse loginResponse;
    private List<Enterprise> userFormData = new ArrayList<>();

    public void generateResponse(String login, String pass){
        this.loginResponse = this.loginService.isUserAllowed(login, pass);

        if(this.loginResponse.isPasswordCorrect()
                && this.loginResponse.isPresent()) {
            if(this.loginResponse.isResearcher()){
                this.userFormData = userFormInitialDataService.getDataForCalculation();
            }
            else if(this.loginResponse.isSecurityManager()){
                //отсутствует логика под безопасника
            }
            else if(this.loginResponse.isHeadResearcher()){
                //отсутствует логика под главного исследователя
            }
            else if(this.loginResponse.isEnterpriseAdmin()){
                //отсутствует логика под администратора предприятия
            }
        } else{

        }
    }
}
