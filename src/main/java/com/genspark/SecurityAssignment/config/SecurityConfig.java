package com.genspark.SecurityAssignment.config;
//Useless comment
import com.genspark.SecurityAssignment.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig
{
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService()
    {
        return new UserService();
    }
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity sec) throws Exception
    {
        sec.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("home/normal")
                .hasRole("NORMAL")
                .requestMatchers("home/admin")
                .hasRole("ADMIN")
                .requestMatchers("/home/public", "/home/add", "/favicon.ico")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
        return sec.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
