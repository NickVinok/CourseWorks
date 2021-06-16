package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.CloudCombustionMode;
import com.diploma.Diploma.DataBase.Model.Territory;
import com.diploma.Diploma.DataBase.Model.User;
import com.diploma.Diploma.DataBase.Repo.UserRepo;
import com.diploma.Diploma.DataBase.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<User> newUser(@RequestBody User user){
        Optional<User> tmp = userService.CreateNewUser(user);
        if(tmp.isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else{
            return ResponseEntity.ok(tmp.get());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        Optional<User> tmp = userService.UpdateUser(user);
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(tmp.get()));
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<User> deleteUser(@RequestBody User user){
        Optional<User> tmp = userService.DeleteUser(user);
        if(tmp.isPresent()){
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
