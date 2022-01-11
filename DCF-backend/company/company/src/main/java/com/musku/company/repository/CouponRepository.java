package com.musku.company.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.musku.company.entity.Coupon;

import java.util.List;

@Repository
public interface CouponRepository extends MongoRepository<Coupon,String> {

    Coupon findCouponsByCode(String code);
    Coupon findCouponsByCouponname(String couponname);
    List<Coupon> findCouponsByCompany(String company);




}
