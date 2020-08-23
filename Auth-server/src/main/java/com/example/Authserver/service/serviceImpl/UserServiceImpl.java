package com.example.Authserver.service.serviceImpl;

import com.example.Authserver.dto.CreateUserRequestDto;
import com.example.Authserver.model.User;
import com.example.Authserver.repository.UserRepository;
import com.example.Authserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    private BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    @Override
    public String SaveUserCredentials(CreateUserRequestDto createUserRequestDto) {

        User newUser=new User();

        newUser.setEmail(createUserRequestDto.getEmail());
        newUser.setPassword(bCryptPasswordEncoder.encode(createUserRequestDto.getPassword()));
        newUser.setAccountNonLocked(true);


        userRepository.save(newUser);

        return newUser.getUserId();

    }
}
