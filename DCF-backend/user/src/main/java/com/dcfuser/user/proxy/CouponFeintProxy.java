package com.dcfuser.user.proxy;

import com.dcfuser.user.entity.Coupon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "coupons")
public interface CouponFeintProxy {

    @GetMapping(path="/coupons/company/{id}")
    public List<Coupon> showCouponsByCompany(@PathVariable String id);

    @GetMapping(path="/coupons/getcouponlist")
    public List<Coupon> showCoupons();
}
