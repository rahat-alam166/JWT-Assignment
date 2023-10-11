package com.genspark.SecurityAssignment.service;

import com.genspark.SecurityAssignment.Dao.UserDao;
import com.genspark.SecurityAssignment.config.UserInfo;
import com.genspark.SecurityAssignment.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService implements UserDetailsService
{
    @Autowired
    private UserDao repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<User> user = repo.findByUsername(username);
        return user.map(UserInfo::new).orElseThrow(()-> new UsernameNotFoundException("user not found"));
    }

}
