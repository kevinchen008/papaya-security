package com.papaya.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.papaya.entity.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    @JsonView(User.userSimpleView.class)
    public List<User> query(@RequestParam String username){
        List<User> users = new ArrayList<>();

        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @GetMapping("/{id:\\d+}")
    @JsonView(User.userDetailView.class)
    public User getUser(@PathVariable String id){
        User user = new User();
        user.setUsername("kevin");
        return user;
    }

    @PostMapping
    public User Create(@Valid @RequestBody User user, BindingResult errors){

        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error ->System.out.println(error.getDefaultMessage()) );
        }
        user.setId("1");
        return  user;
    }
}
