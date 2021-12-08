package com.musku.admin.proxy;

import com.musku.admin.entity.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "company-service")
public interface CompanyFeignProxy {

    @GetMapping("/company/getcompanylist")
    public List<Company> getlist();

    @DeleteMapping(path="/company/deletebyname/{id}")
    public Company deleteByUsername(@PathVariable String id);

}
