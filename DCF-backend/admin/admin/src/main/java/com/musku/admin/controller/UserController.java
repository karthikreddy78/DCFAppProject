package com.musku.admin.controller;


import com.musku.admin.entity.User;
import com.musku.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path="/getuserlist")
    public List<User> showUsers()
    {
        return userService.getAll();
    }

    @PostMapping(path = "/adduser")
    public User postUser(@RequestBody User u)
    {
        return userService.create(u);
    }

    @GetMapping(path="/userid/{id}")
    public User showUserById(@PathVariable int id)
    {
        User u=userService.findUsersById(id);
        if(u==null)
        {
            throw new UserNotFoundException("id="+id);
        }
        return userService.findUsersById(id);
    }

    @GetMapping(path="/username/{id}")
    public User showUserByUsername(@PathVariable String id)
    {
        User u=userService.findByUsername(id);
        if(u==null)
        {
            throw new UserNotFoundException("id="+id);
        }
        System.out.print(u);
        return userService.findByUsername(id);
    }

    @PutMapping(path = "/updateuserbyid/{id}")
    public User updateUserByID(@RequestBody User u,@PathVariable int id)
    {
        User u1=userService.updateById(u,id);
        if(u1==null)
        {
            throw new UserNotFoundException("id="+id);
        }
        return u1;
    }

    @PutMapping(path = "/updateuserbyusername/{id}")
    public User updateUserByUsername(@RequestBody User u,@PathVariable String id)
    {
        User u1=userService.updateByUsername(u,id);
        if(u1==null)
        {
            throw new UserNotFoundException("id="+id);
        }
        return u1;
    }


    @DeleteMapping(path = "/delete")
    public void deleteUsers()
    {
        userService.deleteAll();
    }

    @DeleteMapping(path = "/deletebyid/{id}")
    public User deleteUserByID(@PathVariable int id)
    {
        User u=userService.deleteUserById(id);
        if(u==null)
        {
            throw new UserNotFoundException("id="+id);
        }
        return u;
    }

    @DeleteMapping(path="/deletebyname/{id}")
    public User deleteUserByUsername(@PathVariable String id)
    {

        User u=userService.deleteUserByUsername(id);
        if(u==null)
        {
            throw new UserNotFoundException("id="+id);
        }
        return u;
    }
}
