package com.musku.coupon.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musku.coupon.entity.Category;
import com.musku.coupon.entity.Coupon;
import com.musku.coupon.exception.CouponNotFoundException;
import com.musku.coupon.repository.CouponRepository;
import com.musku.coupon.service.CouponService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Rest Controller class
@RestController
@RequestMapping(path = "/coupons")
public class CouponController {

    public String couponCode;
    @Autowired
    private CouponService couponService;


    @Autowired
    private CouponRepository couponRepository;

    @GetMapping(path = "/hello")
    public String hello() {
        return "hello";
    }

    //Retrieving all the coupons
    @GetMapping(path = "/getcouponlist")
    @CircuitBreaker(name = "default", fallbackMethod = "hardcodedresponse")
    public List<Coupon> showCoupons() {
        System.out.println(couponService.getAll());
        return couponService.getAll();
    }

    //Adding a coupon
    @PostMapping("/addcoupon")
    public ResponseEntity<?> addcoupon(@RequestParam("image") MultipartFile file, @RequestPart(name = "coupon") String coupon)
            throws JsonProcessingException, IOException {
        System.out.println(coupon);
        Coupon coupons = new ObjectMapper().readValue(coupon, Coupon.class);
        System.out.println(coupons);

        coupons.setImagename(file.getOriginalFilename());
        coupons.setImagetype(file.getContentType());
        coupons.setImage(ImageUtility.compressImage(file.getBytes()));
        couponRepository.save(coupons);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ImageUploadResponse("Coupon uploaded successfully: " +
                        file.getOriginalFilename()));
    }



    @PostMapping("/addcoupons/image")
    public ResponseEntity<ImageUploadResponse> uplaodImage(@RequestParam("image") MultipartFile file, @RequestPart(name = "coupon") String coupon)
            throws JsonProcessingException, IOException {
        System.out.println("Data= "+coupon);
        Coupon coupons = new ObjectMapper().readValue(coupon, Coupon.class);
       // System.out.println(coupons);

        coupons.setImagename(file.getOriginalFilename());
        coupons.setImagetype(file.getContentType());
        coupons.setImage(ImageUtility.compressImage(file.getBytes()));
        couponRepository.save(coupons);
        		return ResponseEntity.status(HttpStatus.OK)
                .body(new ImageUploadResponse("Coupon uploaded successfully: " +
                        file.getOriginalFilename()));
    }


    @GetMapping(path = {"/get/image/{name}"})
    public Coupon getCouponDetails(@PathVariable String name) throws IOException {

        //final Optional<Image> dbImage = imageRepository.findByName(name);
        Coupon coupons = couponRepository.findCouponsByCode(name);
        System.out.println(coupons);
        coupons.setImage(ImageUtility.decompressImage(coupons.getImage()));
        return coupons;
    }


    //Getting coupon by code-id
    @GetMapping(path = "/couponid/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "couponresponse")
    public Coupon showCouponById(@PathVariable String id) {
        Coupon u = couponService.findCouponsByCode(id);
        if (u == null) {
            throw new CouponNotFoundException("id=" + id);
        }
        return u;
    }

    //Getting coupons by company name
    @GetMapping(path = "/company/{id}")
   
    public List<Coupon> showCouponsByCompany(@PathVariable String id) {
        List<Coupon> u = couponService.findCouponsByCompany(id);
        if (u == null) {
            throw new CouponNotFoundException("id=" + id);
        }
        System.out.print(u);
        return u;
    }


    //Getting coupons by coupon name
    @GetMapping(path = "/couponname/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "couponresponse")
    public Coupon showCouponByCouponname(@PathVariable String id) {
        Coupon u = couponService.findByCouponname(id);
        if (u == null) {
            throw new CouponNotFoundException("id=" + id);
        }
        System.out.print(u);
        return u;
    }
    
    
    @GetMapping(path = "/category/{id}")
    public List<Coupon> showCouponByCategory(@PathVariable Category id) {
        List<Coupon> u = couponService.findByCategory(id);
        if (u == null) {
            throw new CouponNotFoundException("id=" + id);
        }
        System.out.print(u);
        return u;
    }

    //Updating coupons By Id
//    @PutMapping(path = "/updatecouponbyid/{id}")
//    @CircuitBreaker(name = "default", fallbackMethod = "couponresponse")
//    public Coupon updateCouponByID(@RequestBody Coupon u, @PathVariable String id) {
//        Coupon u1 = couponService.updateById(u, id);
//        if (u1 == null) {
//            throw new CouponNotFoundException("id=" + id);
//        }
//        return u1;
//    }


    @PutMapping(path = "/updatecouponbyid/{id}")
    public ResponseEntity<ImageUploadResponse> updateCoupons(@RequestParam("image") MultipartFile file, @RequestPart(name = "coupon") String coupon)
            throws JsonProcessingException, IOException {
        System.out.println(coupon);
        Coupon u = new ObjectMapper().readValue(coupon, Coupon.class);
        System.out.println(u);

        Coupon u1 = couponRepository.findCouponsByCode(u.getCode());
        if (u1 == null) {
            return null;
        }
        if(u.getCost()!=0)
        	u1.setCost(u.getCost());
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
        if(u.getCategory()!=null)
            u1.setCategory(u.getCategory());
        if(u.getStartDate()!=null)
            u1.setStartDate(u.getStartDate());
        if(u.getUrl()!=null)
            u1.setUrl(u1.getUrl());
        if(file.getBytes()!=null) {
            u1.setImage(ImageUtility.compressImage(file.getBytes()));
            u1.setImagename(file.getName());
            u1.setImagetype(file.getContentType());
        }

         couponRepository.save(u1);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ImageUploadResponse("Coupon uploaded successfully: " +
                        file.getOriginalFilename()));
    }

    //Updating coupons By coupon name
    @PutMapping(path = "/updatecouponbycouponname/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "couponresponse")
    public Coupon updateCouponByCouponname(@RequestBody Coupon u, @PathVariable String id) {
    	System.out.println(u);
        Coupon u1 = couponService.updateByCouponname(u, id);
        if (u1 == null) {
            throw new CouponNotFoundException("id=" + id);
        }
        return u1;
    }
    

    //Deleting all coupons
    @DeleteMapping(path = "/delete")
    public void deleteCoupons() {
        couponService.deleteAll();
    }

    //Delete coupon by coupon code
    @DeleteMapping(path = "/deletebyid/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "couponresponse")
    public Coupon deleteCouponByID(@PathVariable String id) {
        Coupon u = couponService.deleteCouponById(id);
        if (u == null) {
            throw new CouponNotFoundException("id=" + id);
        }
        return u;
    }

    //Delete coupon by coupon name
    @DeleteMapping(path = "/deletebyname/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "couponresponse")
    public Coupon deleteUserByUsername(@PathVariable String id) {

        Coupon u = couponService.deleteCouponByCouponname(id);
        if (u == null) {
            throw new CouponNotFoundException("id=" + id);
        }
        return u;
    }


    //Circuitbreaker method
    public List<Coupon> hardcodedresponse(Exception e) {
        List<Coupon> c = new ArrayList<>();
        c.add(new Coupon());
        return c;
    }

    public Coupon couponresponse(Exception e) {
        Coupon c = new Coupon();
        return c;
    }
}
