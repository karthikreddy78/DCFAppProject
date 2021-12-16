package com.musku.user.controller;

import com.musku.user.entity.Coupon;
import com.musku.user.entity.User;
import com.musku.user.proxy.CouponFeignProxy;
import com.musku.user.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class Controller {

    @Autowired
    private UserService userService;

    @Autowired
    private CouponFeignProxy couponFeintProxy;

    private Logger logger= LoggerFactory.getLogger(Controller.class);

    @GetMapping(path = "/getalllist")
    public List<User> getDetails()
    {
        return userService.getAll();
    }

    @GetMapping(path="/username/{id}")
    @CircuitBreaker(name = "default",fallbackMethod = "usernotfound")
    public User showUserById(@PathVariable String id)
    {
        User u=userService.findById(id);
        if(u==null)
        {
            throw new UserNotFoundException("id="+id);
        }
        return u;
    }

    @GetMapping(path="/email/{id}")
    @CircuitBreaker(name = "default",fallbackMethod = "usernotfound")
    public User showUserByEmail(@PathVariable String id)
    {
        User u=userService.findUserByEmail(id);
        if(u==null)
        {
            throw new UserNotFoundException("id="+id);
        }
        System.out.print(u);
        return u;
    }



    @PutMapping(path = "/updatebyusername/{id}")
    @CircuitBreaker(name = "default",fallbackMethod = "usernotfound")
    public User updateUserByUsername(@RequestBody User u,@PathVariable String id)
    {
        User u1=userService.updateById(u,id);
        if(u1==null)
        {
            throw new UserNotFoundException("id="+id);
        }
        return u1;
    }



    @DeleteMapping(path = "/deletebyname/{id}")
    public User deleteUserByID(@PathVariable String id)
    {
        User u=userService.deleteById(id);
        if(u==null)
        {
            throw new UserNotFoundException("id="+id);
        }
        return u;
    }

    @DeleteMapping(path="/deletebyemail/{id}")
    public User deleteByUsername(@PathVariable String id)
    {

        User u=userService.deleteByEmail(id);
        if(u==null)
        {
            throw new UserNotFoundException("id="+id);
        }
        return u;
    }


    //Coupon Microservice Communication
    @GetMapping(path="/coupons/companyid/{id}")
    @CircuitBreaker(name = "default",fallbackMethod = "hardcodedresponse")
    public List<Coupon> showCouponsByCompany(@PathVariable String id)
    {
        logger.info("retry");
        List<Coupon> coupons = couponFeintProxy.showCouponsByCompany(id);
        System.out.print(coupons);
        return coupons;
    }

    @GetMapping(path="/coupons/couponslist")
    @CircuitBreaker(name = "default",fallbackMethod = "hardcodedresponse")
    public List<Coupon> showCoupons()
    {
        List<Coupon> coupons = couponFeintProxy.showCoupons();
        return coupons;
    }

    public List<Coupon> hardcodedresponse(Exception e)
    {
        List<Coupon> c= new ArrayList<>();
        c.add(new Coupon());
        return c;
    }

    public User usernotfound(Exception e)
    {
        User u= new User();
        return u;
    }





}
