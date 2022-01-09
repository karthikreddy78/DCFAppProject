package com.musku.admin.proxy;

import com.musku.admin.entity.Coupon;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "coupons-service")
public interface CouponFeignProxy {

    @GetMapping(path="/coupons/getcouponlist")
    public List<Coupon> showCoupons();

    @GetMapping(path="/coupons/company/{id}")
    public List<Coupon> showCouponsByCompany(@PathVariable String id);
    
    
    @DeleteMapping(path="/coupons/deletebyname/{id}")
    public Coupon deleteUserByUsername(@PathVariable String id);
    
    @DeleteMapping(path = "/coupons/deletebyid/{id}")
    public Coupon deleteCouponByID(@PathVariable String id);


}
