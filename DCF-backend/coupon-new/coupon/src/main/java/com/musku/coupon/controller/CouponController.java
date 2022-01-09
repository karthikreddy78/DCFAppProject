package com.musku.coupon.controller;

import com.musku.coupon.entity.Coupon;
import com.musku.coupon.exception.CouponNotFoundException;
import com.musku.coupon.service.CouponService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//Rest Controller class
@RestController
@RequestMapping(path = "/coupons")
public class CouponController {

    @Autowired
    private CouponService couponService;

    //Retrieving all the coupons
    @GetMapping(path = "/getcouponlist")
    @CircuitBreaker(name = "default", fallbackMethod = "hardcodedresponse")
    public List<Coupon> showCoupons() {
        System.out.println(couponService.getAll());
        return couponService.getAll();
    }

    //Adding a coupon
    @PostMapping(path = "/addcoupon")
    public Coupon postCoupon(@RequestBody Coupon u) {
        System.out.println(u);
        return couponService.create(u);
    }

    //Getting coupon by code-id
    @GetMapping(path = "/couponid/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "couponresponse")
    public Coupon showCouponById(@PathVariable String id) {
    	System.out.println(id);
        Coupon u = couponService.findCouponsByCode(id);
        System.out.println(u);
        if (u == null) {
            throw new CouponNotFoundException("id=" + id);
        }
        return u;
    }

    //Getting coupons by company name
    @GetMapping(path = "/company/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "hardcodedresponse")
    public List<Coupon> showCouponsByCompany(@PathVariable String id) {
        List<Coupon> u = couponService.findCouponsByCompany(id);
        if (u == null) {
            throw new CouponNotFoundException("id=" + id);
        }
        System.out.print(u);
        return u;
    }


    //Getting coupons by coupon name
    @GetMapping(path = "/couponname/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "couponresponse")
    public Coupon showCouponByCouponname(@PathVariable String id) {
        Coupon u = couponService.findByCouponname(id);
        if (u == null) {
            throw new CouponNotFoundException("id=" + id);
        }
        System.out.print(u);
        return u;
    }

    //Updating coupons By Id
    @PutMapping(path = "/updatecouponbyid/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "couponresponse")
    public Coupon updateCouponByID(@RequestBody Coupon u, @PathVariable String id) {
        Coupon u1 = couponService.updateById(u, id);
        if (u1 == null) {
            throw new CouponNotFoundException("id=" + id);
        }
        return u1;
    }

    //Updating coupons By coupon name
    @PutMapping(path = "/updatecouponbycouponname/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "couponresponse")
    public Coupon updateCouponByCouponname(@RequestBody Coupon u, @PathVariable String id) {
        Coupon u1 = couponService.updateByCouponname(u, id);
        if (u1 == null) {
            throw new CouponNotFoundException("id=" + id);
        }
        return u1;
    }

    //Deleting all coupons
    @DeleteMapping(path = "/delete")
    public void deleteCoupons() {
        couponService.deleteAll();
    }

    //Delete coupon by coupon code
    @DeleteMapping(path = "/deletebyid/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "couponresponse")
    public Coupon deleteCouponByID(@PathVariable String id) {
        Coupon u = couponService.deleteCouponById(id);
        if (u == null) {
            throw new CouponNotFoundException("id=" + id);
        }
        return u;
    }

    //Delete coupon by coupon name
    @DeleteMapping(path = "/deletebyname/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "couponresponse")
    public Coupon deleteUserByUsername(@PathVariable String id) {

        Coupon u = couponService.deleteCouponByCouponname(id);
        if (u == null) {
            throw new CouponNotFoundException("id=" + id);
        }
        return u;
    }


    //Circuitbreaker method
    public List<Coupon> hardcodedresponse(Exception e) {
        List<Coupon> c = new ArrayList<>();
        c.add(new Coupon());
        return c;
    }

    public Coupon couponresponse(Exception e) {
        Coupon c = new Coupon();
        return c;
    }
}
