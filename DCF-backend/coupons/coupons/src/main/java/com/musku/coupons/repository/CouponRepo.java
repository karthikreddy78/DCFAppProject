package com.musku.coupons.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.musku.coupons.entity.Coupon;

@Repository
public interface CouponRepo extends MongoRepository<Coupon,String> {
	
	Coupon findByCouponName(String title);
//
	Coupon findCouponsById(String id);

}
