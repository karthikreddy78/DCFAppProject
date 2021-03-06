package com.musku.user.proxy;


import com.musku.user.entity.Category;
import com.musku.user.entity.Coupon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "coupons-service")
public interface CouponFeignProxy {

    @GetMapping(path="/coupons/getcouponlist")
    public List<Coupon> showCoupons();

    @GetMapping(path="/coupons/company/{id}")
    public List<Coupon> showCouponsByCompany(@PathVariable String id);
    
    @GetMapping(path = "/coupons/category/{id}")
    public List<Coupon> showCouponByCategory(@PathVariable Category id);

}
