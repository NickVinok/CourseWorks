package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.CloudCombustionMode;
import com.diploma.Diploma.DataBase.Model.Territory;
import com.diploma.Diploma.DataBase.Model.User;
import com.diploma.Diploma.DataBase.Repo.UserRepo;
import com.diploma.Diploma.DataBase.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo repo;
    @Autowired
    private UserService userService;

    @PostMapping("/get")
    public Optional<User> getUser(@RequestBody User user){
        return userService.GetOneUser(user);
    }

    @GetMapping()
    public List<User> getUser(){
        return userService.GetAllUsers();
    }

    @GetMapping("/getNew")
    public Territory getNewTerritory(){
        return new Territory();
    }

    @PostMapping("/create")
    public User newUser(@RequestBody User user){
        return userService.CreateNewUser(user);
    }

    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        Optional<User> tmp = userService.UpdateUser(user);
        if(tmp.isPresent()){
            return ResponseEntity.ok(tmp.get());
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/delete")
    public void deleteUser(@RequestBody User user){
        userService.DeleteUser(user);
    }
}
