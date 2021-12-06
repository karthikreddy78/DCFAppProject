//package com.musku.coupons.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.musku.coupons.entity.Coupon;
//import com.musku.coupons.exceptions.CouponNotFoundException;
//import com.musku.coupons.services.CouponService;
//
//@RestController
//@RequestMapping(path = "/coupons")
//public class CouponController {
//	
//	 @Autowired
//	 private CouponService couponService;
//
//   
//
//    @GetMapping(path="/getcouponlist")
//    public List<Coupon> showCoupons()
//    {
//        return couponService.getAll();
//    }
//
//    @PostMapping(path = "/addcoupon")
//    public Coupon postCoupon(@RequestBody Coupon u)
//    {
//        return couponService.create(u);
//    }
//
//    @GetMapping(path="/couponid/{id}")
//    public Coupon showCouponById(@PathVariable String id)
//    {
//        Coupon u=couponService.findCouponsById(id);
//        if(u==null)
//        {
//            throw new CouponNotFoundException("id="+id);
//        }
//        return couponService.findCouponsById(id);
//    }
//
//    @GetMapping(path="/Couponname/{id}")
//    public Coupon showCouponByCouponname(@PathVariable String id)
//    {
//        Coupon u=couponService.findByCouponname(id);
//        if(u==null)
//        {
//            throw new CouponNotFoundException("id="+id);
//        }
//        System.out.print(u);
//        return couponService.findByCouponname(id);
//    }
//
//    @PutMapping(path = "/updateCouponbyid/{id}")
//    public Coupon updateCouponByID(@RequestBody Coupon u,@PathVariable String id)
//    {
//        Coupon u1=couponService.updateById(u,id);
//        if(u1==null)
//        {
//            throw new CouponNotFoundException("id="+id);
//        }
//        return u1;
//    }
//
//    @PutMapping(path = "/updateCouponbyCouponname/{id}")
//    public Coupon updateCouponByCouponname(@RequestBody Coupon u,@PathVariable String id)
//    {
//        Coupon u1=couponService.updateByCouponname(u,id);
//        if(u1==null)
//        {
//            throw new CouponNotFoundException("id="+id);
//        }
//        return u1;
//    }
//
//
//    @DeleteMapping(path = "/delete")
//    public void deleteCoupons()
//    {
//        couponService.deleteAll();
//    }
//
//    @DeleteMapping(path = "/deletebyid/{id}")
//    public Coupon deleteCouponByID(@PathVariable String id)
//    {
//        Coupon u=couponService.deleteCouponById(id);
//        if(u==null)
//        {
//            throw new CouponNotFoundException("id="+id);
//        }
//        return u;
//    }
//
//    @DeleteMapping(path="/deletebyname/{id}")
//    public Coupon deleteCouponByCouponname(@PathVariable String id)
//    {
//
//        Coupon u=couponService.deleteCouponByCouponname(id);
//        if(u==null)
//        {
//            throw new CouponNotFoundException("id="+id);
//        }
//        return u;
//    }
//}