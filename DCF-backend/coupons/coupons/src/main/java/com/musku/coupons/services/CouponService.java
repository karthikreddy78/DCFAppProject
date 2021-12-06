//package com.musku.coupons.services;
//
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.musku.coupons.entity.Coupon;
//import com.musku.coupons.repository.CouponRepository;
//
//@Service
//public class CouponService {
//
//    @Autowired
//    private CouponRepository couponRepository;
//
////    @Autowired
////    private SequenceGeneratorService service;
//
//    //Create operation
//    public Coupon create(Coupon u) {
//       // u.setId(service.getSequenceNumber(SEQUENCE_NAME));
//        return couponRepository.save(u);
//    }
//    //Retrieve operation
//    public List<Coupon> getAll(){
//        return couponRepository.findAll();
//    }
//    public Coupon findCouponsById(String id) {
//        return couponRepository.findCouponsById(id);
//    }
//
//    public Coupon findByCouponname(String Couponname) {
//        return couponRepository.findByCouponName(Couponname);
//    }
//
//
//
//    //Update operation
//    public Coupon updateById(Coupon u,String id) {
//        Coupon u1=couponRepository.findCouponsById(id);
//        if(u1==null)
//        {
//            return null;
//        }
//        if(u.getDescription()!=null)
//            u1.setDescription(u.getDescription());
//        if(u.getEndDate()!=null)
//            u1.setEndDate(u.getEndDate());
//        if(u.getCode()!=(null))
//            u1.setCode(u.getCode());
//        if(u.getTitle()!=(null))
//            u1.setTitle(u.getTitle());
//        return couponRepository.save(u1);
//    }
//
//    public Coupon updateByCouponname(Coupon u,String id) {
//        Coupon u1=couponRepository.findByCouponName(id);
//        if(u1==null)
//        {
//            return null;
//        }
//        if(u.getDescription()!=null)
//            u1.setDescription(u.getDescription());
//        if(u.getEndDate()!=null)
//            u1.setEndDate(u.getEndDate());
//        if(u.getCode()!=(null))
//            u1.setCode(u.getCode());
//        if(u.getTitle()!=(null))
//            u1.setTitle(u.getTitle());
//        return couponRepository.save(u1);
//    }
//    //Delete operation
//    public void deleteAll() {
//        couponRepository.deleteAll();
//    }
//    public Coupon deleteCouponById(String CouponId) {
//        Coupon p = couponRepository.findCouponsById(CouponId);
//        if(p==null)
//            return null;
//        couponRepository.delete(p);
//        return p;
//    }
//
//    public Coupon deleteCouponByCouponname(String id) {
//        Coupon p=couponRepository.findByCouponName(id);
//        if(p==null)
//            return null;
//        couponRepository.delete(p);
//        return p;
//    }
//}
//
