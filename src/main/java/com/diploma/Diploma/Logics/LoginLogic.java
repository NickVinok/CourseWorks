package com.diploma.Diploma.Logics;

import com.diploma.Diploma.DataBase.Model.Enterprise;
import com.diploma.Diploma.DataBase.Model.RolesTableInteraction;
import com.diploma.Diploma.DataBase.Model.Tables;
import com.diploma.Diploma.DataBase.Repo.RolesTableInteractionRepo;
import com.diploma.Diploma.DataBase.Service.LoginService;
import com.diploma.Diploma.DataBase.Service.UserFormInitialDataService;
import com.diploma.Diploma.Utils.LoginResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Data
public class LoginLogic {
    @Autowired
    private LoginService loginService;
    @Autowired
    private RolesTableInteractionRepo rtiRepo;

    @Autowired
    private UserFormInitialDataService userFormInitialDataService;

    private LoginResponse loginResponse;
    private List<Enterprise> designerFormData = new ArrayList<>();
    private HashMap<String, List<RolesTableInteraction>> adminFormData = new HashMap<>();

    public void generateResponse(String login, String pass){
        this.loginResponse = this.loginService.isUserAllowed(login, pass);

        if(this.loginResponse.isPasswordCorrect()
                && this.loginResponse.isPresent()) {
            if(this.loginResponse.getUser().getRole().getName().equals("Проектировщик") ||
                    this.loginResponse.getUser().getRole().getName().equals("Designer") ){
                this.designerFormData = userFormInitialDataService.getDataForCalculation();
            }
            else {
                List<RolesTableInteraction> tmp = rtiRepo.findByRoleIdAndHas(loginResponse.getUser().getRole().getId(),
                        true);
                adminFormData = new HashMap<>();
                for(var entry : tmp){
                    List<RolesTableInteraction> currentList;
                    if(adminFormData.containsKey(entry.getTables().getName())){
                        currentList = adminFormData.get(entry.getTables().getName());
                    } else{
                        currentList = new ArrayList<>();
                    }
                    currentList.add(entry);
                    adminFormData.put(entry.getTables().getName(), currentList);
                }
            }
        }
    }
}
