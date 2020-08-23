package com.example.Authserver.config;

import com.example.Authserver.dto.CustomUserDetails;
import com.example.Authserver.model.User;
import com.example.Authserver.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(CustomUserDetailService.class);

    @Autowired
    private UserRepository userRepository;



    /**
     * @param email
     * @return User object
     * @throws UsernameNotFoundException
     * On login request, User object is taken from Database and passed to Authentication provider
     * If No User object is found, a UsernameNotFoundException is thrown
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("Loading user info from database for email id {}",email);

        User user= Optional.ofNullable(userRepository.findByEmail(email))
                .orElseThrow(()->{
                    logger.error("EmailId does not exist in database");
                    return new UsernameNotFoundException("Invalid Email or Password");}
                    );

        return new CustomUserDetails(user.getUserId(),user.getEmail(),user.getPassword(),user.isAccountNonLocked());
    }
}
