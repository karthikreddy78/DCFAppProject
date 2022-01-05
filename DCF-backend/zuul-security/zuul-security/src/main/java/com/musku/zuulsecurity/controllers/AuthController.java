package com.musku.zuulsecurity.controllers;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musku.zuulsecurity.configs.JwtTokenProvider;
import com.musku.zuulsecurity.models.User;
import com.musku.zuulsecurity.repositories.UserRepository;
import com.musku.zuulsecurity.services.CustomUserDetailsService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository users;

    @Autowired
    private CustomUserDetailsService userService;

    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthBody data) {
        try {
            String username = data.getEmail();
            System.out.println(username);
            try {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.println(
            		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()))
                    		
            		);
            String token = jwtTokenProvider.createToken(username, this.users.findByEmail(username).getRoles());
            User userExists = userService.findUserByEmail(username);
            //userService.updateById(userExists, username, token);
            
            return new ResponseEntity<>(userService.updateById(userExists, username, token), HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email/password supplied");
        }
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
        	 return new ResponseEntity<>("Username exists",HttpStatus.CONFLICT);
        }
       // userService.saveUser(user);
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }
}