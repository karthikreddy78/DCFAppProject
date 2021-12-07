package com.musku.admin.service;


import com.musku.admin.entity.User;
import com.musku.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.musku.admin.entity.User.SEQUENCE_NAME;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SequenceGeneratorService service;

    //Create operation
    public User create(User u) {
        u.setId(service.getSequenceNumber(SEQUENCE_NAME));
        return userRepository.save(u);
    }
    //Retrieve operation
    public List<User> getAll(){
        return userRepository.findAll();
    }
    public User findUsersById(int id) {
        return userRepository.findUsersById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUserName(username);
    }


    public User findByMobileNumber(String mobile) {
        return userRepository.findByMobileNumber(mobile);
    }


    //Update operation
    public User updateById(User u,int id) {
        User u1=userRepository.findUsersById(id);
        if(u1==null)
        {
            return null;
        }
        if(u.getFirstName()!=null)
            u1.setFirstName(u.getFirstName());
        if(u.getLastName()!=null)
            u1.setLastName(u.getLastName());
        if(u.getAge()!=(null))
            u1.setAge(u.getAge());
        if(u.getGender()!=(null))
            u1.setGender(u.getGender());
        if(u.getPassword()!=(null))
            u1.setPassword(u.getPassword());
        return userRepository.save(u1);
    }

    public User updateByUsername(User u,String id) {
        User u1=userRepository.findByUserName(id);
        if(u1==null)
        {
            return null;
        }
        if(u.getFirstName()!=null)
            u1.setFirstName(u.getFirstName());
        if(u.getLastName()!=null)
            u1.setLastName(u.getLastName());
        if(u.getAge()!=(null))
            u1.setAge(u.getAge());
        if(u.getGender()!=(null))
            u1.setGender(u.getGender());
        if(u.getPassword()!=(null))
            u1.setPassword(u.getPassword());
        return userRepository.save(u1);
    }
    //Delete operation
    public void deleteAll() {
        userRepository.deleteAll();
    }
    public User deleteUserById(int userId) {
        User p = userRepository.findUsersById(userId);
        if(p==null)
            return null;
        userRepository.delete(p);
        return p;
    }

    public User deleteUserByUsername(String id) {
        User p=userRepository.findByUserName(id);
        if(p==null)
            return null;
        userRepository.delete(p);
        return p;
    }
}

