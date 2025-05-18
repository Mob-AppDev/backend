package com.slackclone.channel_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8080") // change URL to your user-service port
public interface UserClient {

    @GetMapping("/api/users/{id}")
    Object getUserById(@PathVariable Long id);
}
