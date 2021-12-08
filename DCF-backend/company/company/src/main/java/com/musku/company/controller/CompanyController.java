package com.musku.company.controller;


import com.musku.company.entity.Company;
import com.musku.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/company")
public class CompanyController {


    @Autowired
    private CompanyService companyService;

    @GetMapping("/hello")
    public String hello()
    {
        return "hello";
    }

    @GetMapping("/getcompanylist")
    public List<Company>getlist()
    {
        return companyService.getall();
    }

    @PostMapping(path = "/addcompany")
    public Company postCompany(@RequestBody Company u)
    {
        return companyService.create(u);
    }

    @PutMapping(path = "/updatecompanybyid/{id}")
    public Company updateCompanyByID(@RequestBody Company u,@PathVariable int id)
    {
        Company u1=companyService.updateById(u,id);
        if(u1==null)
        {
            throw new UserNotFoundException("id="+id);
        }
        return u1;
    }

    @PutMapping(path = "/updatecompanybyusername/{id}")
    public Company updateCompanyByCompanyname(@RequestBody Company u,@PathVariable String id)
    {
        Company u1=companyService.updateByCompanyname(u,id);
        if(u1==null)
        {
            throw new UserNotFoundException("id="+id);
        }
        return u1;
    }


//    @DeleteMapping(path = "/delete")
//    public void deleteUsers()
//    {
//        adminService.deleteAll();
//    }

    @DeleteMapping(path = "/deletebyid/{id}")
    public Company deleteUserByID(@PathVariable int id)
    {
        Company u=companyService.deleteCompanyById(id);
        if(u==null)
        {
            throw new UserNotFoundException("id="+id);
        }
        return u;
    }

    @DeleteMapping(path="/deletebyname/{id}")
    public Company deleteByUsername(@PathVariable String id)
    {

        Company u=companyService.deleteCompanyByUsername(id);
        if(u==null)
        {
            throw new UserNotFoundException("id="+id);
        }
        return u;
    }
}
