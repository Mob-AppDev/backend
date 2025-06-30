package com.slackclone.channelmembership_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.slackclone.channelmembership_service.client")
@EnableScheduling
public class ChannelMembershipServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ChannelMembershipServiceApplication.class, args);
	}
}