package com.musku.user;

import com.musku.user.controller.Controller;
import com.musku.user.entity.Coupon;
import com.musku.user.entity.User;
import com.musku.user.proxy.CouponFeignProxy;
import com.musku.user.repository.RoleRepository;
import com.musku.user.repository.UserRepository;
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
class UserApplicationTests {

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
	public void testdeleteCompany()
	{

		when(userRepository.findById("amazon")).thenReturn(java.util.Optional.of(new User("amazon", "amazon@gmail.com")));

		controller.deleteUserByID("amazon");
		verify(userRepository).deleteById("amazon");
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

	@Test
	public void testCouponDetails()
	{
		List<Coupon> details=new ArrayList<>();
		details.add(new Coupon("2","amazon","IDFC2021"));
		details.add(new Coupon("1","amazon","HDFC2021"));
		details.add(new Coupon("3","flipkart","ICCI2021"));

		when(couponFeintProxy.showCoupons()).thenReturn(details);
		assertEquals(3,controller.showCoupons().size());
	}










}
