package com.musku.company.service;


import com.musku.company.entity.Company;
import com.musku.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.musku.company.entity.Company.SEQUENCE_NAME;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private SequenceAdminGenerator service;

    public Company create(Company u) {
        u.setId(service.getSequenceNumber(SEQUENCE_NAME));
        System.out.println(u.getPassword());
        Company save = companyRepository.save(u);
        System.out.println(companyRepository.findCompanyByUserName(u.getUserName()));
        return save;
    }

    public Company updateById(Company u,int id) {
        Company u1=companyRepository.findCompanyById(id);
        if(u1==null)
        {
            return null;
        }
        if(u.getName()!=null)
            u1.setName(u.getName());
        if(u.getPassword()!=(null))
            u1.setPassword(u.getPassword());
        return companyRepository.save(u1);
    }

    public Company updateByCompanyname(Company u, String id) {
        Company u1=companyRepository.findCompanyByUserName(id);
        if(u1==null)
        {
            return null;
        }
        if(u.getName()!=null)
            u1.setName(u.getName());
        if(u.getPassword()!=(null))
            u1.setPassword(u.getPassword());
        return companyRepository.save(u1);
    }

    public Company deleteCompanyById(int userId) {
       Company p =companyRepository.findCompanyById(userId);
        if(p==null)
            return null;
        companyRepository.delete(p);
        return p;
    }

    public Company deleteCompanyByUsername(String id) {
        Company p=companyRepository.findCompanyByUserName(id);
        if(p==null)
            return null;
        companyRepository.delete(p);
        return p;
    }

    public List<Company> getall() {
        return companyRepository.findAll();

    }
}
