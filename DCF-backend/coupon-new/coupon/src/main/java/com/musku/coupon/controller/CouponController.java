package com.musku.coupon.controller;

import com.musku.coupon.entity.Coupon;
import com.musku.coupon.exception.CouponNotFoundException;
import com.musku.coupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/coupons")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @GetMapping(path="/getcouponlist")
    public List<Coupon> showCoupons()
    {
        System.out.println(couponService.getAll());
        return couponService.getAll();
    }

    @PostMapping(path = "/addcoupon")
    public Coupon postCoupon(@RequestBody Coupon u)
    {
        System.out.println(u);
        return couponService.create(u);
    }

    @GetMapping(path="/couponid/{id}")
    public Coupon showCouponById(@PathVariable String id)
    {
        Coupon u=couponService.findCouponsByCode(id);
        if(u==null)
        {
            throw new CouponNotFoundException("id="+id);
        }
        return u;
    }


    @GetMapping(path="/company/{id}")
    public List<Coupon> showCouponsByCompany(@PathVariable String id)
    {
        List<Coupon> u= couponService.findCouponsByCompany(id);
        if(u==null)
        {
            throw new CouponNotFoundException("id="+id);
        }
        System.out.print(u);
        return u;
    }





    @GetMapping(path="/couponname/{id}")
    public Coupon showCouponByCouponname(@PathVariable String id)
    {
        Coupon u=couponService.findByCouponname(id);
        if(u==null)
        {
            throw new CouponNotFoundException("id="+id);
        }
        System.out.print(u);
        return u;
    }

    @PutMapping(path = "/updatecouponbyid/{id}")
    public Coupon updateCouponByID(@RequestBody Coupon u,@PathVariable String id)
    {
        Coupon u1=couponService.updateById(u,id);
        if(u1==null)
        {
            throw new CouponNotFoundException("id="+id);
        }
        return u1;
    }

    @PutMapping(path = "/updatecouponbycouponname/{id}")
    public Coupon updateCouponByCouponname(@RequestBody Coupon u,@PathVariable String id)
    {
        Coupon u1=couponService.updateByCouponname(u,id);
        if(u1==null)
        {
            throw new CouponNotFoundException("id="+id);
        }
        return u1;
    }


    @DeleteMapping(path = "/delete")
    public void deleteCoupons()
    {
        couponService.deleteAll();
    }

    @DeleteMapping(path = "/deletebyid/{id}")
    public Coupon deleteCouponByID(@PathVariable String id)
    {
        Coupon u=couponService.deleteCouponById(id);
        if(u==null)
        {
            throw new CouponNotFoundException("id="+id);
        }
        return u;
    }

    @DeleteMapping(path="/deletebyname/{id}")
    public Coupon deleteUserByUsername(@PathVariable String id)
    {

        Coupon u=couponService.deleteCouponByCouponname(id);
        if(u==null)
        {
            throw new CouponNotFoundException("id="+id);
        }
        return u;
    }
}
