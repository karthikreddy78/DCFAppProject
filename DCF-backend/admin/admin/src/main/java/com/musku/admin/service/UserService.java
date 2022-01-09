package com.musku.admin.service;

import com.musku.admin.entity.Role;
import com.musku.admin.entity.User;
import com.musku.admin.repository.RoleRepository;
import com.musku.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(String id) {
        return userRepository.findById(id).get();
    }

    public User updateById(User u,String id) {
        Optional<User> up=userRepository.findById(id);
        User u1=up.get();
        if(u1==null)
        {
            return null;
        }
        if(u.getFullname()!=null)
            u1.setFullname(u.getFullname());

        return userRepository.save(u1);
    }
    
    public User addUser(User u)
    {
    	if(userRepository.findByEmail(u.getEmail())!=null)
    	{
    		return new User();
    	}
    	else
    	{
    		 u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
    		    u.setEnabled(true);
    		    //Role userRole = roleRepository.findByRole("ADMIN");
    		    System.out.println(u.getRoles());
    			String rolet=u.getToken();
    			if(rolet==null)
    			{
    				Role userRole = roleRepository.findByRole("USER");
    				u.setRoles((new HashSet<>(Arrays.asList(userRole))));
    			}
    			else
    			{
    				Role userRole = roleRepository.findByRole(rolet);
    				u.setRoles(new HashSet<>(Arrays.asList(userRole)));

    			}
    		return userRepository.save(u);
    	}
    }

    public User deleteById(String userId) {
        Optional<User> p1=userRepository.findById(userId);
        User p=p1.get();
        if(p==null)
            return null;
        userRepository.delete(p);
        return p;
    }

    public User deleteByEmail(String id) {
       User p=userRepository.findByEmail(id);
        if(p==null)
            return null;
        userRepository.delete(p);
        return p;
    }


    public List<User> getAll() {
        return userRepository.findAll();
    }

    public List<User> findList(String role) {

        Role r=roleRepository.findByRole(role);
        System.out.println(r);
        Set<Role> roles= new HashSet<>();
        roles.add(r);
        return userRepository.findByRolesIn(roles);
    }
}
