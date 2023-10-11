package com.genspark.SecurityAssignment.controller;

import com.genspark.SecurityAssignment.entity.CustomUserDetails;
import com.genspark.SecurityAssignment.entity.User;
import com.genspark.SecurityAssignment.service.UserCreateService;
import com.genspark.SecurityAssignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class MyController
{
    @Autowired
    private UserCreateService serv;

    @Autowired
    private UserService userService;
    @GetMapping("/public")
    public String publicUser()
    {
        return "<HTML><H1> I am public user <H1><HTML>";
    }

    @GetMapping("/admin")
    public String adminUser()
    {
        return "<HTML><H1> I am Admin <H1><HTML>";
    }
    @PostMapping("/add")
    public String addUser(@RequestBody User user)
    {
        return serv.addUser(user);
    }

    @GetMapping("/normal")
    public String normalUser()
    {
        return "<HTML><H1> I am normal <H1><HTML>";
    }

    @GetMapping("/details")
    public String customDetails()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails user = userService.loadUserByUsername(authentication.getName());
        return user.getAuthorities().toString();
    }


}
