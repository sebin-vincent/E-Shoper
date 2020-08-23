package com.example.Authserver.controller;

import com.nimbusds.jose.jwk.JWKSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class JksController {

    private Logger logger = LoggerFactory.getLogger(JksController.class);

    @Autowired
    private JWKSet jwkSet;

    @GetMapping("/jwks-json")
    public Map<String, Object> keys() {

        return this.jwkSet.toJSONObject();
    }



}
