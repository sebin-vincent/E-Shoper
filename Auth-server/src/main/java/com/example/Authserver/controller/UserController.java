package com.example.Authserver.controller;

import com.example.Authserver.dto.CreateUserRequestDto;
import com.example.Authserver.dto.ResponseDTO;
import com.example.Authserver.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private Environment environment;

    @GetMapping("/user/signup")
    public ResponseEntity<ResponseDTO> createUser(){

        logger.info("Environment port "+environment.getProperty("local.server.port"));

        CreateUserRequestDto createUserRequestDto1=new CreateUserRequestDto();

        createUserRequestDto1.setEmail("Agnavincent@gmail.com");
        createUserRequestDto1.setPassword("password");

        ResponseDTO responseDTO=new ResponseDTO();

        responseDTO.setPayload(userService.SaveUserCredentials(createUserRequestDto1));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }



}
