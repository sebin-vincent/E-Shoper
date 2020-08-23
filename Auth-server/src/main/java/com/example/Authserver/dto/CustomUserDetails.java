package com.example.Authserver.dto;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Setter @NoArgsConstructor
public class CustomUserDetails implements UserDetails {

    private String userId;

    private String email;

    private String password;

    private boolean locked;

    public String getUserId(){
        return this.userId;
    }

    public CustomUserDetails(String userId, String email, String password, boolean locked) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.locked = locked;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
