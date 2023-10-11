package com.genspark.SecurityAssignment.service;

import com.genspark.SecurityAssignment.Dao.UserDao;
import com.genspark.SecurityAssignment.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserCreateService
{
    @Autowired
    private UserDao repo;
    @Autowired
    private PasswordEncoder pEncoder;
    public String addUser(User user)
    {
        user.setPassword(pEncoder.encode(user.getPassword()));
        repo.save(user);
        return "User added";
    }
}
