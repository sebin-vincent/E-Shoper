package com.example.Authserver.service;

import com.example.Authserver.dto.CreateUserRequestDto;

public interface UserService {

    String SaveUserCredentials(CreateUserRequestDto user);
}
