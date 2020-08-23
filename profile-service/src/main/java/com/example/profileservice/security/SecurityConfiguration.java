package com.example.profileservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableResourceServer
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends ResourceServerConfigurerAdapter {

    Logger logger=LoggerFactory.getLogger(SecurityConfiguration.class);

    /**
     * Dynamicaly add public urls from application property during server startup
     */
    @Value("${resources.security.public.urls}")
    private String[] publicURLArray;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPointImpl;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        List<String> publicURLs= Arrays.asList(publicURLArray);

        for(String publicURL: publicURLs){
            http.authorizeRequests().antMatchers(publicURL).permitAll();
        }

        http.authorizeRequests()
                .anyRequest().authenticated().
                and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);

    }


    /**
     * @return LoggedInUser- create object of current user with scope request
     */
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public LoggedInUser getLoggedInUserDetails() {
        LoggedInUser loggedInUser=new LoggedInUser();

        Map<String, Claim> claims = new HashMap<>();

        String tokenValue = ((OAuth2AuthenticationDetails)(SecurityContextHolder.getContext().getAuthentication()).getDetails()).getTokenValue();
        try {
            DecodedJWT jwt = JWT.decode(tokenValue);
            claims = jwt.getClaims();
        } catch (JWTDecodeException ex) {
            logger.error(ex.getMessage());
        }

        loggedInUser.setUserId(claims.get("userId").asString());


        return loggedInUser;

    }

}
