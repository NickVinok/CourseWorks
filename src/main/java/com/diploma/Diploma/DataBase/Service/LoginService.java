package com.diploma.Diploma.DataBase.Service;

import com.diploma.Diploma.DataBase.Model.User;
import com.diploma.Diploma.DataBase.Repo.UserRepo;
import com.diploma.Diploma.Utils.LoginResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
public class LoginService {
    @Autowired
    private UserRepo userRepo;

    public LoginResponse isUserAllowed(String login, String password){
        LoginResponse response = new LoginResponse();
        Optional<User> user = userRepo.findByLogin(login);
        //Если такой пользователь не существует
        if(user.isEmpty()) {
            response.setPresent(false);
        }
        //Если такой пользователь существует
        else {
            response.setPresent(true);
            //Если пароль соответсвует логину
            System.out.println(user);
            if (user.get().getPassword().equals(password)){
                response.setPasswordCorrect(true);
                //Если роль пользователя - админ
                if(user.get().getRole().getName().equals("enterpriseAdmin")){
                    response.setEnterpriseAdmin(true);
                    response.setResearcher(false);
                    response.setHeadResearcher(false);
                    response.setSecurityManager(false);
                }
                //Если роль пользователя - исседователь
                else if(user.get().getRole().getName().equals("researcher")){
                    response.setEnterpriseAdmin(false);
                    response.setResearcher(true);
                    response.setHeadResearcher(false);
                    response.setSecurityManager(false);
                }
                else if(user.get().getRole().getName().equals("headResearcher")){
                    response.setEnterpriseAdmin(false);
                    response.setResearcher(false);
                    response.setHeadResearcher(true);
                    response.setSecurityManager(false);
                }
                else if(user.get().getRole().getName().equals("securityManager")){
                    response.setEnterpriseAdmin(false);
                    response.setResearcher(false);
                    response.setHeadResearcher(false);
                    response.setSecurityManager(true);
                }
            }
            //Если пароль неверен
            else{
                response.setPresent(true);
                response.setPasswordCorrect(false);
            }
        }
        return response;
    }
}
