package com.slackclone.channel_service.client;

import com.slackclone.channel_service.dto.UserDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8080")
public interface UserClient {

    @GetMapping("/hello")
    String sayHelloFromUserService();

    @GetMapping("/users/{id}")
    UserDto getUserById(@PathVariable("id") @NotNull(message = "createdByUserId is required") Long createdByUserId);



}

