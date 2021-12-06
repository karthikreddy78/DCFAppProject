package com.dcfuser.user.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository rep;

    public List<Customer> retrieveDetails()
    {
        List<Customer> customer = new ArrayList<>();
        rep.findAll().forEach(customer::add);
        return customer;
    }

}
