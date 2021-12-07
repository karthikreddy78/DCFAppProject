package com.musku.admin.controller;

import com.musku.admin.entity.Admin;
import com.musku.admin.entity.User;
import com.musku.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

//    @GetMapping(path="/getadminlist")
//    public List<Admin> showAdmin()
//    {
//        return adminService.getAll();
//    }

    @GetMapping("/hello")
    public String hello()
    {
        return "hello";
    }


    @GetMapping(path="/getlist")
    public List<Admin> showUsers()
    {
        return adminService.getAll();
    }

    @GetMapping(path="/getadminlist")
    public List<Admin> showAdmins()
    {
        return adminService.getAdmin();
    }

    @PostMapping(path = "/addadmin")
    public Admin postAdmin(@RequestBody Admin u)
    {
        return adminService.create(u);
    }

//    @GetMapping(path="/userid/{id}")
//    public User showUserById(@PathVariable int id)
//    {
//        User u=userService.findUsersById(id);
//        if(u==null)
//        {
//            throw new UserNotFoundException("id="+id);
//        }
//        return userService.findUsersById(id);
//    }

//    @GetMapping(path="/username/{id}")
//    public User showUserByUsername(@PathVariable String id)
//    {
//        User u=userService.findByUsername(id);
//        if(u==null)
//        {
//            throw new UserNotFoundException("id="+id);
//        }
//        System.out.print(u);
//        return userService.findByUsername(id);
//    }

    @PutMapping(path = "/updateadminbyid/{id}")
    public Admin updateUserByID(@RequestBody Admin u,@PathVariable int id)
    {
        Admin u1=adminService.updateById(u,id);
        if(u1==null)
        {
            throw new UserNotFoundException("id="+id);
        }
        return u1;
    }

    @PutMapping(path = "/updateadminbyusername/{id}")
    public Admin updateUserByUsername(@RequestBody Admin u,@PathVariable String id)
    {
        Admin u1=adminService.updateByAdminname(u,id);
        if(u1==null)
        {
            throw new UserNotFoundException("id="+id);
        }
        return u1;
    }


//    @DeleteMapping(path = "/delete")
//    public void deleteUsers()
//    {
//        adminService.deleteAll();
//    }

    @DeleteMapping(path = "/deletebyid/{id}")
    public Admin deleteUserByID(@PathVariable int id)
    {
        Admin u=adminService.deleteAdminById(id);
        if(u==null)
        {
            throw new UserNotFoundException("id="+id);
        }
        return u;
    }

    @DeleteMapping(path="/deletebyname/{id}")
    public Admin deleteUserByUsername(@PathVariable String id)
    {

        Admin u=adminService.deleteAdminByUsername(id);
        if(u==null)
        {
            throw new UserNotFoundException("id="+id);
        }
        return u;
    }
}
