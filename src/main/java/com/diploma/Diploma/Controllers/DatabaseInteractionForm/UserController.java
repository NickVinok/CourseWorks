package com.diploma.Diploma.Controllers.DatabaseInteractionForm;

import com.diploma.Diploma.DataBase.Model.User;
import com.diploma.Diploma.DataBase.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepo repo;

    @PostMapping("/get")
    public Optional<User> getUser(@RequestBody User user){
        return repo.findById(user.getId());
    }

    @GetMapping()
    public List<User> getUser(){
        return repo.findAll();
    }

    @PostMapping("/create")
    public User newUser(@RequestBody User user){
        return repo.save(user);
    }

    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        Optional<User> tmp = repo.findById(user.getId());
        if(tmp.isPresent()){
            return ResponseEntity.ok(repo.save(user));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/delete")
    public void deleteUser(@RequestBody User user){
        repo.deleteById(user.getId());
    }
}
