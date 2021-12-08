package com.musku.coupon.repository;

import com.musku.coupon.entity.Coupon;
import com.musku.coupon.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends MongoRepository<Coupon,Integer> {

    Coupon findCouponsById(int Id);
    Coupon findCouponsByCouponname(String couponname);
    List<Coupon> findCouponsByCompany(String company);


}
