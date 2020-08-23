package com.example.profileservice.controller;

import com.example.profileservice.dto.CreateUserRequestDto;
import com.example.profileservice.proxyservices.authService.UserServiceProxy;
import com.example.profileservice.service.UserService;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.profileservice.dto.ResponseDTO;

@RestController
public class UserController {

    Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    Environment environment;

    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceProxy userServiceProxy;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO> signup(@RequestBody CreateUserRequestDto user){

        ResponseDTO responseDTO=new ResponseDTO();
        String port = environment.getProperty("local.server.port");

        ResponseDTO proxyServiceResponse=(ResponseDTO) (userServiceProxy.createUser()).getBody();

        String userId=(String) proxyServiceResponse.getPayload();

        user.setUserId(userId);

        userService.CreateUser(user);

        responseDTO.setPayload(new String("Created user with userId "+userId));

        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

}
