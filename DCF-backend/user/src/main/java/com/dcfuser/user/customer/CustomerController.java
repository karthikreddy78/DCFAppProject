package com.dcfuser.user.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CustomerController {
	@Autowired
	private CustomerRepository rep;

	@Autowired
	private CustomerService service;

	@GetMapping(path="/customers")
	public List<Customer> postDetails() {
//		List<Customer> customer = new ArrayList<>();
//		rep.findAll().forEach(customer::add);
//		return customer;

		return service.retrieveDetails();
	}

	@PostMapping(path = "/customers")
	public void postDetails(@RequestBody Customer cust) {
		rep.save(cust);
	}
	
	@GetMapping(path = "/customers/{id}")
	public Optional<Customer> getDetail(@PathVariable int id) {
		return rep.findById(id);
	}

	@PutMapping(path = "/customers/{id}")
	public void setDetail(@RequestBody Customer cust, @PathVariable String id) {
		// System.out.println(service.getTopic(id));

		rep.save(cust);
	}

	@DeleteMapping(path = "/customers/{id}")
	public void deleteDetail(@RequestBody Customer cust, @PathVariable int id) {
		// System.out.println(service.getTopic(id));
		rep.deleteById(id);
	}


}
