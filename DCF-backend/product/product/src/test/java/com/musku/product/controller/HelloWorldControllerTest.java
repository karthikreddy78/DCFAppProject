package com.musku.product.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HelloWorldControllerTest {

	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Test
	void hello()
	{
		HelloWorldController controller= new HelloWorldController();
		String s=controller.HelloWorld();
		assertEquals(s, "Hello World");
	}

}
