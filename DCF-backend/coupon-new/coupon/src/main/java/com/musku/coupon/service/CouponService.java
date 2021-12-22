package com.musku.coupon.service;

import com.musku.coupon.entity.Coupon;
import com.musku.coupon.repository.CouponRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;


    //Create operation
    public Coupon create(Coupon u)
    {
        // u.setId(service.getSequenceNumber(SEQUENCE_NAME));
        System.out.println(u);
        Coupon p = couponRepository.findCouponsByCode(u.getCode());
        if (p == null) {
            return couponRepository.save(u);
        }
        return p;

    }

    //Retrieve operation
    public List<Coupon> getAll()
    {
        return couponRepository.findAll();
    }

    //Find Coupon BY code
    public Coupon findCouponsByCode(String code)
    {
        return couponRepository.findCouponsByCode(code);
    }
    //find By Couponname
    public Coupon findByCouponname(String username)
    {
        return couponRepository.findCouponsByCouponname(username);
    }

    //Coupon By company
    public List<Coupon> findCouponsByCompany(String company)
    {
        return couponRepository.findCouponsByCompany(company);
    }

    //Update operation
    public Coupon updateById(Coupon u, String id) {
        Coupon u1 = couponRepository.findCouponsByCode(id);
        if (u1 == null) {
            return null;
        }
        if (u.getCompany() != null)
            u1.setCompany(u.getCompany());
        if (u.getDescription() != null)
            u1.setDescription(u.getDescription());
        if (u.getEndDate() != null)
            u1.setEndDate(u.getEndDate());
        if (u.getCode() != (null))
            u1.setCode(u.getCode());
        if (u.getCouponname() != (null))
            u1.setCouponname(u.getCouponname());
        if (u.getOffer() != 0)
            u1.setOffer(u.getOffer());
        return couponRepository.save(u1);
    }

    //Update By Coupon Name
    public Coupon updateByCouponname(Coupon u, String id)
    {
        Coupon u1 = couponRepository.findCouponsByCouponname(id);
        if (u1 == null) {
            return null;
        }
        if (u.getCompany() != null)
            u1.setCompany(u.getCompany());
        if (u.getDescription() != null)
            u1.setDescription(u.getDescription());
        if (u.getEndDate() != null)
            u1.setEndDate(u.getEndDate());
        if (u.getCode() != (null))
            u1.setCode(u.getCode());
        if (u.getCouponname() != (null))
            u1.setCouponname(u.getCouponname());
        if (u.getOffer() != 0)
            u1.setOffer(u.getOffer());
        return couponRepository.save(u1);
    }

    //Delete operation
    public void deleteAll() {
        couponRepository.deleteAll();
    }

    public Coupon deleteCouponById(String id) {
        Coupon p = couponRepository.findCouponsByCode(id);
        if (p == null)
            return null;
        couponRepository.deleteById(id);
        return p;
    }

    //Delete By Coupon Name
    public Coupon deleteCouponByCouponname(String id) {
        Coupon p = couponRepository.findCouponsByCouponname(id);
        if (p == null)
            return null;
        couponRepository.deleteById(p.getCode());
        return p;
    }
}


