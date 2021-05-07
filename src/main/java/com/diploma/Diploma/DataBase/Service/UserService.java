package com.diploma.Diploma.DataBase.Service;

import com.diploma.Diploma.DataBase.Model.User;
import com.diploma.Diploma.DataBase.Repo.UserRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class UserService {
    @Autowired
    private UserRepo repo;
    @Autowired
    private HashService hashService;

    public Optional<User> CreateNewUser(User u){
        Optional<User> tmp = repo.findById(u.getId());
        if(tmp.isPresent()){
            return Optional.empty();
        } else {
            String pass = u.getPassword();
            try {
                u.setPassword(hashService.encode(u.getPassword()));
            } catch(Exception ignored){
                u.setPassword(pass);
            }
            return Optional.of(repo.save(u));
        }
    }

    public List<User> GetAllUsers(){
        return repo.findAll();
    }

    public Optional<User> GetOneUser(User u){
        return repo.findById(u.getId());
    }

    public Optional<User> UpdateUser(User u){
        Optional<User> user = repo.findById(u.getId());
        if(user.isPresent()){
            String pass = user.get().getPassword();
            try {
                user.get().setPassword(hashService.encode(u.getPassword()));
            } catch(Exception ignored) {
                user.get().setPassword(pass);
            }
            return Optional.of(repo.save(user.get()));
        } else{
            return Optional.empty();
        }
    }

    public Optional<User> DeleteUser(User u){
        Optional<User> tmp = repo.findById(u.getId());
        if(tmp.isPresent()){
            repo.delete(u);
            return tmp;
        } else{
            return Optional.empty();
        }
    }
}
