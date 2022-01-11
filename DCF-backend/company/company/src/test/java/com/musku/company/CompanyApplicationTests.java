package com.musku.company;

import com.musku.company.controller.Controller;
import com.musku.company.entity.Coupon;
import com.musku.company.entity.User;
import com.musku.company.proxy.CouponFeintProxy;
import com.musku.company.repository.RoleRepository;
import com.musku.company.repository.UserRepository;
import com.musku.company.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CompanyApplicationTests {

	@Test
	void contextLoads() {
	}
	@MockBean
	private UserRepository userRepository;

	@MockBean
	private RoleRepository roleRepository;

	@MockBean
	private CouponFeintProxy couponFeintProxy;

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


//	@Test
//	public void testAddCoupon()
//	{
//		Coupon c=new Coupon("2","amazon","IDFC2021");
//		when(couponFeintProxy.postCoupon(c)).thenReturn(c);
//		assertEquals("IDFC2021",controller.postCouponN(c).getCouponname());
//	}

	@Test
	public void testdeleteCoupon()
	{

		when(couponFeintProxy.deleteUserByUsername("IDFC2021")).thenReturn(new Coupon("2","amazon","IDFC2021"));

		controller.deleteUserByUsername("IDFC2021");
		verify(couponFeintProxy).deleteUserByUsername("IDFC2021");
	}




}
