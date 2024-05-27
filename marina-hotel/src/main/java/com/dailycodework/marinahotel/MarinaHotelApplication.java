package com.dailycodework.marinahotel;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication


public class MarinaHotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarinaHotelApplication.class, args);
	}

}
