package com.ecommerce.ecommerce_api.service;

import com.ecommerce.ecommerce_api.dto.UserDto;
import com.ecommerce.ecommerce_api.model.User;
import com.ecommerce.ecommerce_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> findAll() {
        return UserDto.of(this.userRepository.findAll());
    }

    public Optional<UserDto> findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            return Optional.of(UserDto.of(user.get()));
        }
        return Optional.empty();
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }
}
