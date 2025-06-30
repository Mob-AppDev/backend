package com.slackclone.message_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.slackclone.message_service.model.User;

@FeignClient(name = "user-service", url = "http://localhost:8081")
public interface UserClient {
    @GetMapping("/api/users/{id}")
    User getUserById(@PathVariable("id") long id);
}
