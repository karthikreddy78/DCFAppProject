package com.musku.coupon.service;

import com.musku.coupon.entity.Coupon;
import com.musku.coupon.repository.CouponRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.musku.coupon.entity.User.SEQUENCE_NAME;
@Service
public class CouponService {

    //private static final String SEQUENCE_NAME = ;
    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private SequenceGeneratorService service;

    //Create operation
    public Coupon create(Coupon u) {
        //u.setId(service.getSequenceNumber(SEQUENCE_NAME));
        return couponRepository.save(u);
    }
    //Retrieve operation
    public List<Coupon> getAll(){
        return couponRepository.findAll();
    }
    public Coupon findCouponsById(int id) {
        return couponRepository.findCouponsById(id);
    }

    public Coupon findByCouponname(String username) {
        return couponRepository.findCouponsByCouponname(username);
    }


    //Update operation
    public Coupon updateById(Coupon u,int id) {
        Coupon u1=couponRepository.findCouponsById(id);
        if(u1==null)
        {
            return null;
        }

        if(u.getDescription()!=null)
            u1.setDescription(u.getDescription());
        if(u.getEndDate()!=null)
            u1.setEndDate(u.getEndDate());
        if(u.getCode()!=(null))
            u1.setCode(u.getCode());
        if(u.getTitle()!=(null))
            u1.setTitle(u.getTitle());
        return couponRepository.save(u1);
    }

    public Coupon updateByCouponname(Coupon u,String id) {
       Coupon u1=couponRepository.findCouponsByCouponname(id);
        if(u1==null)
        {
            return null;
        }

        if(u.getDescription()!=null)
            u1.setDescription(u.getDescription());
        if(u.getEndDate()!=null)
            u1.setEndDate(u.getEndDate());
        if(u.getCode()!=(null))
            u1.setCode(u.getCode());
        if(u.getTitle()!=(null))
            u1.setTitle(u.getTitle());

        return couponRepository.save(u1);
    }
    //Delete operation
    public void deleteAll() {
        couponRepository.deleteAll();
    }
    public Coupon deleteCouponById(int id) {
        Coupon p = couponRepository.findCouponsById(id);
        if(p==null)
            return null;
        couponRepository.delete(p);
        return p;
    }

    public Coupon deleteCouponByCouponname(String id) {
        Coupon p=couponRepository.findCouponsByCouponname(id);
        if(p==null)
            return null;
        couponRepository.delete(p);
        return p;
    }
}


