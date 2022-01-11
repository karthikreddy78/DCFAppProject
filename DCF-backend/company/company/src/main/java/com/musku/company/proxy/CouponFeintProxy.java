package com.musku.company.proxy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.musku.company.entity.Coupon;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@FeignClient(name = "coupons-service")
public interface CouponFeintProxy {

    @GetMapping(path="/coupons/company/{id}")
    public List<Coupon> showCouponsByCompany(@PathVariable String id);
    
    @GetMapping(path = "/coupons/couponname/{id}")
    public Coupon showCouponByCouponname(@PathVariable String id);

    @PostMapping(path = "/coupons/addcoupon")
    public ResponseEntity<?> addcoupon(@RequestParam("image") MultipartFile file, @RequestPart(name = "coupon") String coupon)
            throws JsonProcessingException, IOException;

    @PutMapping(path = "/coupons/updatecouponbycouponname/{id}")
    public Coupon updateCouponByCouponname(@RequestBody Coupon u,@PathVariable String id);
    


    @DeleteMapping(path="/coupons/deletebyname/{id}")
    public Coupon deleteUserByUsername(@PathVariable String id);
    
    @GetMapping(path = "/coupons/couponid/{id}")
    public Coupon showCouponById(@PathVariable String id);
    
    @DeleteMapping(path = "/coupons/deletebyid/{id}")
    public Coupon deleteCouponByID(@PathVariable String id);

    
   

}
