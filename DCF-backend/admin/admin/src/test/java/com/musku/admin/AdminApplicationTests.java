package com.musku.admin;

import com.musku.admin.controller.Controller;
import com.musku.admin.entity.Coupon;
import com.musku.admin.entity.User;
import com.musku.admin.proxy.CouponFeignProxy;
import com.musku.admin.repository.RoleRepository;
import com.musku.admin.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class AdminApplicationTests {

	@Test
	void contextLoads() {
	}

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private RoleRepository roleRepository;

	@MockBean
	private CouponFeignProxy couponFeintProxy;

	@Autowired
	private Controller controller;


	@Test
	public void testCompanyByID() {
		String id="amazon";
		when(userRepository.findById(id)).thenReturn(java.util.Optional.of(new User("amazon")));

		assertEquals("amazon",controller.showUserById("amazon").getId());


	}

	@Test
	public void findCompanybyEmail()
	{
		String email="amazon@gmail.com";
		when(userRepository.findByEmail(email))
				.thenReturn(new User("amazon","amazon@gmail.com"));

		assertEquals(email,controller.showUserByEmail(email).getEmail());
	}



	@Test
	public void testCouponsByCompany()
	{
		String company="amazon";
		List<Coupon> details=new ArrayList<>();
		details.add(new Coupon("2","amazon","IDFC2021"));
		details.add(new Coupon("1","amazon","HDFC2021"));
		when(couponFeintProxy.showCouponsByCompany(company)).thenReturn(details);

		assertEquals(2,controller.showCouponsByCompany("amazon").size());
	}








}
