package com.musku.coupon;

import com.musku.coupon.entity.Coupon;
import com.musku.coupon.repository.CouponRepository;
import com.musku.coupon.service.CouponService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
class CouponApplicationTests {

	@Test
	void contextLoads() {
	}

	@MockBean
	CouponRepository couponRepository;

	@Autowired
	CouponService couponService;

	@Test
	public void getCoupons()
	{
		when(couponRepository.findAll()).thenReturn(Stream.of(new Coupon("1"))
				.collect(Collectors.toList())
		);
		assertEquals(1,couponService.getAll().size());
	}

	@Test
	public void findCouponsById() {
		String id="1";
		when(couponRepository.findCouponsByCode(id)).thenReturn(new Coupon("1"));

		assertEquals("1",couponRepository.findCouponsByCode(id).getCode());


	}

	@Test
	public void findCouponsbyName()
	{
		String coupon="IDFC2021";
		when((couponRepository.findCouponsByCouponname(coupon)))
				.thenReturn(new Coupon("2","amazon","IDFC2021"));

		assertEquals(coupon,couponService.findByCouponname(coupon).getCouponname());
	}

	@Test
	public void testCouponsByCompany()
	{
		String company="amazon";

		List<Coupon> l=new ArrayList<>();
		l.add(new Coupon("2","amazon","IDFC2021"));
		l.add(new Coupon("1","amazon","HDFC2021"));
		when(couponRepository.findCouponsByCompany(company)).thenReturn(l);
		assertEquals(2,couponService.findCouponsByCompany(company).size());
	}

	@Test
	public void testAddCoupon()
	{
		Coupon c=new Coupon("2","amazon","IDFC2021");
		when(couponRepository.save(c)).thenReturn(c);
		assertEquals("IDFC2021",couponService.create(c).getCouponname());
	}

	@Test
	public void testdeleteCoupon()
	{

		when(couponRepository.findCouponsByCode("2")).thenReturn(new Coupon("2","amazon","IDFC2021"));

		couponService.deleteCouponById("2");
		verify(couponRepository).deleteById("2");
	}




}
