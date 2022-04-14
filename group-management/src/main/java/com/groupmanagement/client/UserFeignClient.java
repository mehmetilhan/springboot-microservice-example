package com.groupmanagement.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author mehmet
 */
@FeignClient(name = "UserFeignClient", url = "http://127.0.0.1:8081/auth")
public interface UserFeignClient {


    @PostMapping("/validate")
    Boolean validateToken(String token);

    @GetMapping("/get-user-id-by-token/{token}")
    ResponseEntity<Long> getUserId(@PathVariable String token);

}
