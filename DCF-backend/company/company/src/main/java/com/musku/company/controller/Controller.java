package com.musku.company.controller;

import com.musku.company.entity.Coupon;
import com.musku.company.entity.User;
import com.musku.company.proxy.CouponFeintProxy;
import com.musku.company.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/company")
public class Controller {

    @Autowired
    private UserService userService;

    //Feign Proxy CLient
    @Autowired
    private CouponFeintProxy couponFeintProxy;

    //Logger class
    private Logger logger = LoggerFactory.getLogger(Controller.class);

    //Get By username
    @GetMapping(path = "/username/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "companyresponse")
    public User showUserById(@PathVariable String id) {
        User u = userService.findById(id);
        if (u == null) {
            throw new UserNotFoundException("id=" + id);
        }
        return u;
    }

    //Get By email
    @GetMapping(path = "/email/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "companyresponse")
    public User showUserByEmail(@PathVariable String id) {
        User u = userService.findUserByEmail(id);
        if (u == null) {
            throw new UserNotFoundException("id=" + id);
        }
        System.out.print(u);
        return u;
    }


    //Update By Username
    @PutMapping(path = "/updatebyusername/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "companyresponse")
    public User updateUserByUsername(@RequestBody User u, @PathVariable String id) {
        User u1 = userService.updateById(u, id);
        if (u1 == null) {
            throw new UserNotFoundException("id=" + id);
        }
        return u1;
    }


    //DeleteById
    @DeleteMapping(path = "/deletebyname/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "companyresponse")
    public User deleteUserByID(@PathVariable String id) {
        User u = userService.deleteById(id);
        if (u == null) {
            throw new UserNotFoundException("id=" + id);
        }
        return u;
    }

    //Delete By Email
    @DeleteMapping(path = "/deletebyemail/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "companyresponse")
    public User deleteByUsername(@PathVariable String id) {

        User u = userService.deleteByEmail(id);
        if (u == null) {
            throw new UserNotFoundException("id=" + id);
        }
        return u;
    }


    //Coupon Microservice Communication

    //Get coupons of the company
    @GetMapping(path = "/coupons/companyid/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "hardcodedresponse")
    public List<Coupon> showCouponsByCompany(@PathVariable String id) {
        logger.info("retry");
        List<Coupon> coupons = couponFeintProxy.showCouponsByCompany(id);
        System.out.print(coupons);
        return coupons;
    }
    
    @GetMapping(path="coupons/couponname/{id}")
    public Coupon showCByCN(@PathVariable String id)
    {
    	System.out.println(couponFeintProxy.showCouponByCouponname(id));
    	return couponFeintProxy.showCouponByCouponname(id);
    }

    //Post Coupon
    @PostMapping(path = "/coupons/addcoupon")
    public Coupon postCouponN(@RequestBody Coupon u)
    {
    	System.out.println(u);
    	System.out.println((u.getCouponname()));
    	String id=u.getCouponname();
    	//System.out.print(couponFeintProxy.showCouponByCouponname(id));
//    	if(couponFeintProxy.showCouponByCouponname(u.getCouponname())!=null)
//    	{
//    		//System.out.println((couponFeintProxy.showCouponById(u.getCode())));
//    		//return new ResponseEntity<>("Coupon exists",HttpStatus.CONFLICT);
//    		return new Coupon();
//    	}
        // userService.saveUser(user);
       // return new ResponseEntity<>(couponFeintProxy.postCoupon(u), HttpStatus.CREATED);
    	return couponFeintProxy.postCoupon(u);
    }

    //Update coupon
    @PutMapping(path = "/coupons/updatecouponbycouponname/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "couponresponse")
    public Coupon updateCouponByCouponname(@RequestBody Coupon u, @PathVariable String id)
    {
        return couponFeintProxy.updateCouponByCouponname(u, id);
    }

    //Delete coupon by name
    @DeleteMapping(path = "/coupons/deletebyname/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "couponresponse")
    public Coupon deleteUserByUsername(@PathVariable String id)
    {
    	System.out.println("Delee COuponBYName:"+id);

        return couponFeintProxy.deleteUserByUsername(id);
    }
    
    @DeleteMapping(path = "/coupons/deletebyid/{id}")
    public Coupon deleteCouponByID(@PathVariable String id)
    {

        return couponFeintProxy.deleteCouponByID(id);
    }
    

    //Circuit breaker
    public List<Coupon> hardcodedresponse(Exception e) {
        List<Coupon> c = new ArrayList<>();
        c.add(new Coupon());
        return c;
    }

    public User companyresponse(Exception e)
    {
        User u= new User();
        return u;
    }

    public Coupon couponresponse(Exception e)
    {
        Coupon c=new Coupon();
        return c;
    }


}
