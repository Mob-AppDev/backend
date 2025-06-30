package com.slackclone.channelmembershipservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main application class for the Channel Membership Service.
 * 
 * Note: This application requires the JVM argument --enable-native-access=ALL-UNNAMED
 * to avoid warnings related to restricted method access in Tomcat.
 * 
 * When running the application manually, use:
 * java --enable-native-access=ALL-UNNAMED -jar channelmembership_service.jar
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.slackclone.channelmembershipservice.client")
@EnableScheduling
public class ChannelMembershipServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ChannelMembershipServiceApplication.class, args);
	}
}
