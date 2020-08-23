package com.example.Authserver.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "user")
@Getter @Setter @NoArgsConstructor
public class User {

    @Id
    @Field(name = "_id")
    private String userId;

    private String email;

    private String password;

    private boolean accountNonLocked;
}
