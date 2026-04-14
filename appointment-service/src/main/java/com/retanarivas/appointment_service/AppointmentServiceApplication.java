package com.retanarivas.appointment_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.retanarivas.appointment_service", "com.retanarivas.common"})
public class AppointmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentServiceApplication.class, args);
	}

}
