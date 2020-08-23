package com.example.Authserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CreateUserRequestDto {

    private String userId;

    private String name;

    private String email;

    private String password;

    private int age;

    private String address;

}
