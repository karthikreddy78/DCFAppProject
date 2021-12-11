package com.musku.admin.proxy;

import com.musku.admin.entity.Coupon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "coupons")
public interface CouponFeignProxy {

    @GetMapping(path="/coupons/getcouponlist")
    public List<Coupon> showCoupons();

    @GetMapping(path="/coupons/company/{id}")
    public List<Coupon> showCouponsByCompany(@PathVariable String id);

}
