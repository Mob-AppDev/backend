package com.slackclone.channel_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.slackclone.channel_service.client")
@EnableScheduling
public class ChannelServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ChannelServiceApplication.class, args);
	}
}
