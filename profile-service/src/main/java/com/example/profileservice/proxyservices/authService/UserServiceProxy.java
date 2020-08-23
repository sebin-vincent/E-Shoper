package com.example.profileservice.proxyservices.authService;

import com.example.profileservice.dto.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "auth-server")
public interface UserServiceProxy {

    @GetMapping("/user/signup")
    public ResponseEntity<ResponseDTO> createUser();

}
