package com.slackclone.channel_service.client;

import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user-service", url = "http://localhost:8081")
public interface UserClient {

    @GetMapping("/hello")
    String sayHelloFromUserService();

    void getUserById(@NotNull(message = "createdByUserId is required") Long createdByUserId);
}

