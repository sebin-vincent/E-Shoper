package com.example.profileservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@Getter @Setter @NoArgsConstructor
public class User {

    @Id
    private String _id;

    private String userName;

    private int age;

    private String address;

}
