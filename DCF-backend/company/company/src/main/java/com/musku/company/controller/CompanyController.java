//package com.musku.company.controller;
//
//
//import com.musku.company.entity.Company;
//import com.musku.company.entity.Coupon;
//import com.musku.company.proxy.CouponFeintProxy;
//import com.musku.company.service.CompanyService;
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping(path = "/company")
//public class CompanyController {
//
//
//    @Autowired
//    private CompanyService companyService;
//
//    @Autowired
//    private CouponFeintProxy couponFeintProxy;
//
//    private Logger logger= LoggerFactory.getLogger(CompanyController.class);
//    @GetMapping("/hello")
//    public String hello()
//    {
//        return "hello";
//    }
//
//    @GetMapping("/getcompanylist")
//    public List<Company>getlist()
//    {
//        return companyService.getall();
//    }
//
//    @PostMapping(path = "/addcompany")
//    public Company postCompany(@RequestBody Company u)
//    {
//        return companyService.create(u);
//    }
//
//    @PutMapping(path = "/updatecompanybyid/{id}")
//    public Company updateCompanyByID(@RequestBody Company u,@PathVariable int id)
//    {
//        Company u1=companyService.updateById(u,id);
//        if(u1==null)
//        {
//            throw new UserNotFoundException("id="+id);
//        }
//        return u1;
//    }
//
//    @PutMapping(path = "/updatecompanybyusername/{id}")
//    public Company updateCompanyByCompanyname(@RequestBody Company u,@PathVariable String id)
//    {
//        Company u1=companyService.updateByCompanyname(u,id);
//        if(u1==null)
//        {
//            throw new UserNotFoundException("id="+id);
//        }
//        return u1;
//    }
//
//
////    @DeleteMapping(path = "/delete")
////    public void deleteUsers()
////    {
////        adminService.deleteAll();
////    }
//
//    @DeleteMapping(path = "/deletebyid/{id}")
//    public Company deleteUserByID(@PathVariable int id)
//    {
//        Company u=companyService.deleteCompanyById(id);
//        if(u==null)
//        {
//            throw new UserNotFoundException("id="+id);
//        }
//        return u;
//    }
//
//    @DeleteMapping(path="/deletebyname/{id}")
//    public Company deleteByUsername(@PathVariable String id)
//    {
//
//        Company u=companyService.deleteCompanyByUsername(id);
//        if(u==null)
//        {
//            throw new UserNotFoundException("id="+id);
//        }
//        return u;
//    }
//
//    //Coupon Microservice Communication
//    @GetMapping(path="/coupons/companyid/{id}")
//    @CircuitBreaker(name = "default",fallbackMethod = "hardcodedresponse")
//    public List<Coupon> showCouponsByCompany(@PathVariable String id)
//    {
//        logger.info("retry");
//        List<Coupon> coupons = couponFeintProxy.showCouponsByCompany(id);
//        System.out.print(coupons);
//        return coupons;
//    }
//
//    @PostMapping(path = "/coupons/addcoupon")
//    public Coupon postCouponN(@RequestBody Coupon u)
//    {
//        return couponFeintProxy.postCoupon(u);
//    }
//
//    @PutMapping(path = "/coupons/updatecouponbycouponname/{id}")
//    public Coupon updateCouponByCouponname(@RequestBody Coupon u,@PathVariable String id)
//    {
//        return couponFeintProxy.updateCouponByCouponname(u,id);
//    }
//
//    @DeleteMapping(path="/coupons/deletebyname/{id}")
//    public Coupon deleteUserByUsername(@PathVariable String id)
//    {
//        return couponFeintProxy.deleteUserByUsername(id);
//    }
//
//    public List<Coupon> hardcodedresponse(Exception e)
//    {
//        List<Coupon> c= new ArrayList<>();
//        c.add(new Coupon());
//        return c;
//    }
//
//
//
//
//
//
//
//
//}
