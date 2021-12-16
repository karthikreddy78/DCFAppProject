package com.musku.coupon.controller;

import com.musku.coupon.entity.Coupon;
import com.musku.coupon.repository.CouponRepository;
import com.musku.coupon.service.CouponService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CouponControllerTest {

    @Mock
    CouponRepository couponRepository;

    @Autowired
    CouponService couponService;

    @Test
    public List<Coupon> testshowCoupons()
    {
        List<Coupon> coupons=new ArrayList<>();
        coupons.add(new Coupon());
        System.out.println(couponService.getAll().size());
        when(couponRepository.findAll()).thenReturn(coupons);
        assertEquals(couponService.getAll().size(),4);
        return coupons;
    }

}