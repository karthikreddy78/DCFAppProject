package com.musku.zuulsecurity.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.musku.zuulsecurity.models.Role;
import com.musku.zuulsecurity.models.User;
import com.musku.zuulsecurity.repositories.RoleRepository;
import com.musku.zuulsecurity.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
	
	public User findUserByEmail(String email) {
	    return userRepository.findByEmail(email);
	}
	public void saveUser(User user) {
	    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	    user.setEnabled(true);
	    //Role userRole = roleRepository.findByRole("ADMIN");
	    System.out.println(user.getRoles());
	    Iterator i=user.getRoles().iterator();
		if(user.getRoles().size()==0)
		{
			Role userRole = roleRepository.findByRole("USER");
			user.setRoles((new HashSet<>(Arrays.asList(userRole))));
		}
	    while (i.hasNext()) {   
            Role r=(Role) i.next();
			if(r.getRole()==null)
			{
				Role userRole = roleRepository.findByRole("USER");
				user.setRoles((new HashSet<>(Arrays.asList(userRole))));
			}
			else
			{
				if(r.getRole().equals("ADMIN"))
				{
					Role userRole = roleRepository.findByRole("ADMIN");
					user.setRoles(new HashSet<>(Arrays.asList(userRole)));

				}
				else if(r.getRole().equals("COMPANY"))
				{
					Role userRole = roleRepository.findByRole("COMPANY");
					user.setRoles(new HashSet<>(Arrays.asList(userRole)));

				}
				else
				{
					Role userRole = roleRepository.findByRole("USER");
					user.setRoles(new HashSet<>(Arrays.asList(userRole)));

				}
			}
        }  

	   
	    //user.setRoles(new HashSet<>(Arrays.asList(userRole)));
	    userRepository.save(user);
	    System.out.println(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

	    User user = userRepository.findByEmail(email);
	    if(user != null) {
	        List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
	        return buildUserForAuthentication(user, authorities);
	    } else {
	        throw new UsernameNotFoundException("username not found");
	    }
	}
	
	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
	    Set<GrantedAuthority> roles = new HashSet<>();
	    userRoles.forEach((role) -> {
	        roles.add(new SimpleGrantedAuthority(role.getRole()));
	    });

	    List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
	    return grantedAuthorities;
	}
	
	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
	    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}
}