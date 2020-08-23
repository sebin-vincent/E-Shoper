package com.example.Authserver.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Custom implimentation for authentication provider.
 * Can be user to control maximum number of consecutive failed attempts to login, log all requests login requests
 */
@Component
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    Logger logger= LoggerFactory.getLogger(CustomAuthenticationProvider.class);


    @Autowired
    @Override
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        super.setUserDetailsService(userDetailsService);
    }

    @Autowired
    @Override
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        super.setPasswordEncoder(passwordEncoder);
    }


    /**
     * @param authentication
     * @return Authentication
     * @throws AuthenticationException
     *
     * Do the actual authentication
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        logger.info("Authentication attempt for {}",authentication.getName());


        try{
            Authentication auth=super.authenticate(authentication);

            logger.info("Authentication attempt for {} Successful",authentication.getName());
            return auth;
        }catch (BadCredentialsException exception){

            logger.info("Authentication attempt for {} failed",authentication.getName());
            throw new BadCredentialsException("Invalid username or password");
        }catch (AuthenticationException exception){

            logger.info(exception.getMessage());
            throw exception;
        }

    }
}
