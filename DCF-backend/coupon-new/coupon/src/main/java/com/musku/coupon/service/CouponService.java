package com.musku.coupon.service;

import com.musku.coupon.controller.ImageUtility;
import com.musku.coupon.entity.Category;
import com.musku.coupon.entity.Coupon;
import com.musku.coupon.repository.CouponRepository;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;


    //Create operation
//    public Coupon create(Coupon u) throws IOException
//    {
//        // u.setId(service.getSequenceNumber(SEQUENCE_NAME));
//        System.out.println(u);
//        Coupon p = couponRepository.findCouponsByCode(u.getCode());
//
//        if (p == null) {
//            return couponRepository.save(u);
//        }
//        return p;
//
//    }



    //Retrieve operation
    public List<Coupon> getAll()
    {
        List<Coupon> coupons = couponRepository.findAll();

        System.out.println(coupons);
        coupons.forEach((c)->c.setImage(ImageUtility.decompressImage(c.getImage())));
        System.out.println(coupons);
        return coupons;
    }

    //Find Coupon BY code
    public Coupon findCouponsByCode(String code)
    {
        Coupon coupons = couponRepository.findCouponsByCode(code);
        System.out.println(coupons);
        coupons.setImage(ImageUtility.decompressImage(coupons.getImage()));
        return coupons;
    }
    //find By Couponname
    public Coupon findByCouponname(String username)
    {
        Coupon coupons = couponRepository.findCouponsByCouponname(username);
        System.out.println(coupons);
        coupons.setImage(ImageUtility.decompressImage(coupons.getImage()));
        return coupons;
    }

    //Coupon By company
    public List<Coupon> findCouponsByCompany(String company)
    {
        List<Coupon> coupons = couponRepository.findCouponsByCompany(company);

        System.out.println(coupons);
        coupons.forEach((c)->c.setImage(ImageUtility.decompressImage(c.getImage())));
        System.out.println(coupons);
        return coupons;
    }
    
    public List<Coupon> findByCategory(Category category) {
    	List<Coupon> coupons = couponRepository.findByCategory(category);
    	System.out.println(coupons);

        coupons.forEach((c)->c.setImage(ImageUtility.decompressImage(c.getImage())));
    	
        System.out.println(coupons);
		// TODO Auto-generated method stub
		return coupons;
	}



    //Update operation
    public Coupon updateById(Coupon u, MultipartFile file) throws IOException {
        Coupon u1 = couponRepository.findCouponsByCode(u.getCode());
        if (u1 == null) {
            return null;
        }
        if (u.getCompany() != null)
            u1.setCompany(u.getCompany());
        if (u.getDescription() != null)
            u1.setDescription(u.getDescription());
        if (u.getEndDate() != null)
            u1.setEndDate(u.getEndDate());
        if (u.getCouponname() != (null))
            u1.setCouponname(u.getCouponname());
        if (u.getOffer() != 0)
            u1.setOffer(u.getOffer());
       
        if(u.getStartDate()!=null)
            u1.setStartDate(u.getStartDate());
        if(u.getUrl()!=null)
            u1.setUrl(u1.getUrl());
        if(file.getBytes()!=null) {
            u1.setImage(ImageUtility.compressImage(file.getBytes()));
            u1.setImagename(file.getName());
            u1.setImagetype(file.getContentType());
        }

        return couponRepository.save(u1);
    }

    //Update By Coupon Name
    public Coupon updateByCouponname(Coupon u, String id)
    {
        Coupon u1 = couponRepository.findCouponsByCouponname(id);
        System.out.println("Coupon Service="+u1);
        if (u1 == null) {
            return null;
        }
        if(u.getCategory()!=null)
            u1.setCategory(u.getCategory());
        if (u.getCompany() != null)
            u1.setCompany(u.getCompany());
        if (u.getDescription() != null)
            u1.setDescription(u.getDescription());
        if (u.getEndDate() != null)
            u1.setEndDate(u.getEndDate());
        if (u.getOffer() != 0)
            u1.setOffer(u.getOffer());
            if(u.getStartDate()!=null)
                u1.setStartDate(u.getStartDate());
            if(u.getUrl()!=null)
                u1.setUrl(u1.getUrl());
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


