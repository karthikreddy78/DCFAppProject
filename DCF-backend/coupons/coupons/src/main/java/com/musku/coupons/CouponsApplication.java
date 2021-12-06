package com.musku.coupons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication(exclude = EmbeddedMongoAutoConfiguration.class)
@EnableMongoRepositories
@EnableEurekaClient
public class CouponsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponsApplication.class, args);
	}

}
