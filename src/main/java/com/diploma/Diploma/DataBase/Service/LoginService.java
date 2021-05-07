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
    @Autowired
    private HashService hashService;

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
            String hashedPass;
            try {
                hashedPass = hashService.encode(password);
            }catch (Exception e) {
                hashedPass = password;
            }
            if(user.get().getPassword().equals(hashedPass)){
                response.setPasswordCorrect(true);
                response.setUser(user.get());
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
