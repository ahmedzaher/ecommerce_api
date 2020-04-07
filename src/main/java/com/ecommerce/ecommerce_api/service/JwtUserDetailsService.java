package com.ecommerce.ecommerce_api.service;

import com.ecommerce.ecommerce_api.configuration.AuthUserDetails;
import com.ecommerce.ecommerce_api.repository.AuthUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private AuthUserRepository authUserRepository;

    public JwtUserDetailsService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return authUserRepository.findByUsername(username)
                .map(AuthUserDetails::new)
                .orElseThrow( () -> new UsernameNotFoundException("User not found"));
    }
}
