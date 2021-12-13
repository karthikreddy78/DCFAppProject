//package com.musku.admin.proxy;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@FeignClient(name = "company")
//public interface CompanyFeignProxy {
//
//    @GetMapping("/company/getcompanylist")
//    public List<Company> getlist();
//
//    @DeleteMapping(path="/company/deletebyname/{id}")
//    public Company deleteByUsername(@PathVariable String id);
//
//    @PostMapping(path = "/company/addcompany")
//    public Company postCompany(@RequestBody Company u);
//
//}
