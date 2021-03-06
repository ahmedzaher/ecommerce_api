package com.ecommerce.ecommerce_api.configuration;

import com.ecommerce.ecommerce_api.model.AuthUser;
import com.ecommerce.ecommerce_api.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AuthUserDetails implements UserDetails {

    private String username;
    private String password;
    private List<GrantedAuthority> authorities;
    private User systemUser;
    private boolean enabled;

    public AuthUserDetails(AuthUser user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.enabled = user.isEnabled();

        this.authorities = Arrays.stream( user.getRoles().split(",") )
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        this.systemUser = user.getSystemUser();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public User getSystemUser() {
        return systemUser;
    }
}
