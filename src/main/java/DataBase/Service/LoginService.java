package DataBase.Service;

import DataBase.Model.User;
import DataBase.Repo.UserRepo;
import Utils.LoginResponse;
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
            //Если пароль соответсвует логину
            if (user.get().getPassword().equals(password)){
                response.setPasswordCorrect(true);
                //Если роль пользователя - админ
                if(user.get().getRole().getName().equals("admin")){
                    response.setPresent(true);
                    response.setAdmin(true);
                    response.setResearcher(false);
                }
                //Если роль пользователя - исседователь
                else if(user.get().getRole().getName().equals("researcher")){
                    response.setPresent(true);
                    response.setAdmin(false);
                    response.setResearcher(true);
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
