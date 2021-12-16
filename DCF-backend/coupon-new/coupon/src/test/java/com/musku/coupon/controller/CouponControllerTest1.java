//package com.musku.coupon.controller;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//
//import com.musku.coupon.entity.Coupon;
//import com.musku.coupon.repository.CouponRepository;
//import com.musku.coupon.service.CouponService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//class CouponControllerTest1 {
//
//
//    @MockBean
//    CouponRepository couponRepository;
//
//    @Autowired
//    CouponController couponController;
//
//
//    @Test
//    public void getAllCouponsTest() {
//        when(couponRepository.findAll()).thenReturn(
//                Stream.of(
//                                new Coupon(1))
//                        .collect(Collectors.toList()));
//        assertEquals(1, couponController.showCoupons().size());
//
//    }
//
//
//}
