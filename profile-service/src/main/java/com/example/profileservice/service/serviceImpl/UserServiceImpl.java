package com.example.profileservice.service.serviceImpl;

import com.example.profileservice.dto.CreateUserRequestDto;
import com.example.profileservice.model.User;
import com.example.profileservice.repository.UserRepository;
import com.example.profileservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void CreateUser(CreateUserRequestDto user) {

        logger.info("preparing user object");

        User newUser=new User();

        newUser.set_id(user.getUserId());
        newUser.setUserName(user.getName());
        newUser.setAge(user.getAge());
        newUser.setAddress(user.getAddress());

        userRepository.save(newUser);
        logger.info("saving user object");
        
    }
}
