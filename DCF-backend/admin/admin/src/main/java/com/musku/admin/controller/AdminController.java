//package com.musku.admin.controller;
//
//import com.musku.admin.proxy.CompanyFeignProxy;
//import com.musku.admin.proxy.CouponFeignProxy;
//import com.musku.admin.proxy.UserFeignProxy;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//@RestController
//@RequestMapping(path = "/admin")
//public class AdminController {
//
//    @Autowired
//    private AdminService adminService;
//
//    @Autowired
//    private UserFeignProxy userFeignProxy;
//
//    @Autowired
//    private CouponFeignProxy couponFeignProxy;
//
//    @Autowired
//    private CompanyFeignProxy companyFeignProxy;
//
////    @GetMapping(path="/getadminlist")
////    public List<Admin> showAdmin()
////    {
////        return adminService.getAll();
////    }
//
//    @GetMapping("/hello")
//    public String hello()
//    {
//        return "hello";
//    }
//
//
//    @GetMapping(path="/users/userlist")
//    public List<User> getlist()
//    {
//        List<User> users = userFeignProxy.showUsers();
//        return users;
//    }
//
//    @DeleteMapping(path="/users/deletebyname/{id}")
//    public User deleteUsersByUsername(@PathVariable String id)
//    {
//        return userFeignProxy.deleteUserByUsername(id);
//    }
//
////Coupon Communication Microservice
//    @GetMapping(path="/coupons/couponslist")
//    public List<Coupon> getcouponlist()
//    {
//        List<Coupon> coupons = (List<Coupon>) couponFeignProxy.showCoupons();
//        return coupons;
//    }
//
//    @GetMapping(path="/coupons/company/{id}")
//    public List<Coupon> showCouponsByCompany(@PathVariable String id)
//    {
//        List<Coupon> coupons = couponFeignProxy.showCouponsByCompany(id);
//        System.out.print(coupons);
//        return coupons;
//    }
//
//
//
//    //Company Microservice Communication
//
//    @GetMapping(path="/company/companylist")
//    public List<Company> getcompanylist()
//    {
//        List<Company> company = companyFeignProxy.getlist();
//        return company;
//    }
//
//    @DeleteMapping(path="/company/deletebyname/{id}")
//    public Company deleteByUsername(@PathVariable String id)
//    {
//
//        return companyFeignProxy.deleteByUsername(id);
//    }
//
//    @PostMapping(path = "/company/addcompany")
//    public Company postCompany(@RequestBody Company u)
//    {
//        return companyFeignProxy.postCompany(u);
//    }
//
//
//
//
//
//
//
//    //Admin CRUD
//    @GetMapping(path="/getlist")
//    public List<Admin> showUsers()
//    {
//        return adminService.getAll();
//    }
//
//    @GetMapping(path="/getadminlist")
//    public List<Admin> showAdmins()
//    {
//        return adminService.getAdmin();
//    }
//
//    @PostMapping(path = "/addadmin")
//    public Admin postAdmin(@RequestBody Admin u)
//    {
//        return adminService.create(u);
//    }
//
////    @GetMapping(path="/userid/{id}")
////    public User showUserById(@PathVariable int id)
////    {
////        User u=userService.findUsersById(id);
////        if(u==null)
////        {
////            throw new UserNotFoundException("id="+id);
////        }
////        return userService.findUsersById(id);
////    }
//
////    @GetMapping(path="/username/{id}")
////    public User showUserByUsername(@PathVariable String id)
////    {
////        User u=userService.findByUsername(id);
////        if(u==null)
////        {
////            throw new UserNotFoundException("id="+id);
////        }
////        System.out.print(u);
////        return userService.findByUsername(id);
////    }
//
//    @PutMapping(path = "/updateadminbyid/{id}")
//    public Admin updateUserByID(@RequestBody Admin u,@PathVariable int id)
//    {
//        Admin u1=adminService.updateById(u,id);
//        if(u1==null)
//        {
//            throw new UserNotFoundException("id="+id);
//        }
//        return u1;
//    }
//
//    @PutMapping(path = "/updateadminbyusername/{id}")
//    public Admin updateUserByUsername(@RequestBody Admin u,@PathVariable String id)
//    {
//        Admin u1=adminService.updateByAdminname(u,id);
//        if(u1==null)
//        {
//            throw new UserNotFoundException("id="+id);
//        }
//        return u1;
//    }
//
//
////    @DeleteMapping(path = "/delete")
////    public void deleteUsers()
////    {
////        adminService.deleteAll();
////    }
//
//    @DeleteMapping(path = "/deletebyid/{id}")
//    public Admin deleteUserByID(@PathVariable int id)
//    {
//        Admin u=adminService.deleteAdminById(id);
//        if(u==null)
//        {
//            throw new UserNotFoundException("id="+id);
//        }
//        return u;
//    }
//
//    @DeleteMapping(path="/deletebyname/{id}")
//    public Admin deleteUserByUsername(@PathVariable String id)
//    {
//
//        Admin u=adminService.deleteAdminByUsername(id);
//        if(u==null)
//        {
//            throw new UserNotFoundException("id="+id);
//        }
//        return u;
//    }
//}
