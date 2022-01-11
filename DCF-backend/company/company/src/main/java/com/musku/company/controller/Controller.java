package com.musku.company.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musku.company.comments.ImageUploadResponse;
import com.musku.company.comments.ImageUtility;
import com.musku.company.entity.Coupon;
import com.musku.company.entity.User;
import com.musku.company.proxy.CouponFeintProxy;
import com.musku.company.repository.CouponRepository;
import com.musku.company.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/company")
public class Controller {

    @Autowired
    private UserService userService;

    //Feign Proxy CLient
    @Autowired
    private CouponFeintProxy couponFeintProxy;
    
    @Autowired
    private CouponRepository couponRepository;

    //Logger class
    private Logger logger = LoggerFactory.getLogger(Controller.class);
    
    
    @PutMapping(path = "/coupons/updatecouponbycouponname/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "couponresponse")
    public Coupon updateCouponByCouponname(@RequestBody Coupon u, @PathVariable String id)
    {
    	System.out.println(id+" "+u);
        return couponFeintProxy.updateCouponByCouponname(u, id);
    }

    //Get By username
    @GetMapping(path = "/username/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "companyresponse")
    public User showUserById(@PathVariable String id) {
        User u = userService.findById(id);
        if (u == null) {
            throw new UserNotFoundException("id=" + id);
        }
        return u;
    }

    //Get By email
    @GetMapping(path = "/email/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "companyresponse")
    public User showUserByEmail(@PathVariable String id) {
        User u = userService.findUserByEmail(id);
        if (u == null) {
            throw new UserNotFoundException("id=" + id);
        }
        System.out.print(u);
        return u;
    }


    //Update By Username
    @PutMapping(path = "/updatebyusername/{id}")
    public User updateUserByUsername(@RequestBody User u, @PathVariable String id) {
    	System.out.println("Company="+u);
        User u1 = userService.updateById(u, id);
        if (u1 == null) {
            throw new UserNotFoundException("id=" + id);
        }
        return u1;
    }


    //DeleteById
    @DeleteMapping(path = "/deletebyname/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "companyresponse")
    public User deleteUserByID(@PathVariable String id) {
        User u = userService.deleteById(id);
        if (u == null) {
            throw new UserNotFoundException("id=" + id);
        }
        return u;
    }

    //Delete By Email
    @DeleteMapping(path = "/deletebyemail/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "companyresponse")
    public User deleteByUsername(@PathVariable String id) {

        User u = userService.deleteByEmail(id);
        if (u == null) {
            throw new UserNotFoundException("id=" + id);
        }
        return u;
    }


    //Coupon Microservice Communication

    //Get coupons of the company
    @GetMapping(path = "/coupons/companyid/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "hardcodedresponse")
    public List<Coupon> showCouponsByCompany(@PathVariable String id) {
        logger.info("retry");
        List<Coupon> coupons = couponFeintProxy.showCouponsByCompany(id);
        //System.out.print(coupons);
        return coupons;
    }
    
    @GetMapping(path="/coupons/couponname/{id}")
    public Coupon showCByCN(@PathVariable String id)
    {
    	System.out.println(couponFeintProxy.showCouponByCouponname(id));
    	return couponFeintProxy.showCouponByCouponname(id);
    }

    //Post Coupon
    @PostMapping(path = "/coupons/addcoupon")
    public ResponseEntity<?> addcoupon(@RequestParam("image") MultipartFile file, @RequestPart(name = "coupon") String coupon)
            throws JsonProcessingException, IOException
    {
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

    //Update coupon
  
    
    
    
    @PostMapping(path = "/coupons/updatecouponbyid")
    public ResponseEntity<?> updateCoupons(@RequestParam("image") MultipartFile file, @RequestPart(name = "coupon") String coupon)
            throws JsonProcessingException, IOException {
        System.out.println(coupon);
        Coupon u = new ObjectMapper().readValue(coupon, Coupon.class);
        System.out.println(u);

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

         

        return new ResponseEntity<>(couponRepository.save(u1),HttpStatus.OK);
    }
    
    @PostMapping("/coupons/addcoupons/image")
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

    //Delete coupon by name
    @DeleteMapping(path = "/coupons/deletebyname/{id}")
    @CircuitBreaker(name = "default", fallbackMethod = "couponresponse")
    public Coupon deleteUserByUsername(@PathVariable String id)
    {
    	System.out.println("Delee COuponBYName:"+id);

        return couponFeintProxy.deleteUserByUsername(id);
    }
    
    @DeleteMapping(path = "/coupons/deletebyid/{id}")
    public Coupon deleteCouponByID(@PathVariable String id)
    {

        return couponFeintProxy.deleteCouponByID(id);
    }
    

    //Circuit breaker
    public List<Coupon> hardcodedresponse(Exception e) {
        List<Coupon> c = new ArrayList<>();
        c.add(new Coupon());
        return c;
    }

    public User companyresponse(Exception e)
    {
        User u= new User();
        return u;
    }

    public Coupon couponresponse(Exception e)
    {
        Coupon c=new Coupon();
        return c;
    }


}
