package com.musku.company.service;

import com.musku.company.entity.User;
import com.musku.company.repository.RoleRepository;
import com.musku.company.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(String id) {
        return userRepository.findById(id).get();
    }

    public User updateById(User u,String id) {
        Optional<User> up=userRepository.findById(id);
       
        User u1=up.get();
        System.out.println(u1);
        if(u1==null)
        {
            return null;
        }
        if(u.getPassword()!=null)
        {
        	if(!(u.getPassword().equals(u1.getPassword())))
        	 u1.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
        }
        if(u.getFullname()!=null)
            u1.setFullname(u.getFullname());

        return userRepository.save(u1);
    }

    public User deleteById(String userId) {
        Optional<User> p1=userRepository.findById(userId);
        User p=p1.get();
        if(p==null)
            return null;
        userRepository.deleteById(p.getId());
        return p;
    }

    public User deleteByEmail(String id) {
       User p=userRepository.findByEmail(id);
        if(p==null)
            return null;
        userRepository.deleteById(p.getId());
        return p;
    }






}
