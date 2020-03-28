package com.ecommerce.ecommerce_api.dto;

import com.ecommerce.ecommerce_api.model.User;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class UserDto {

    Long id;
    String name;

    public static UserDto of(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    public static List<UserDto> of(List<User> users) {
        List usersDto = new ArrayList();
        if (users != null) {
            users.forEach(user -> usersDto.add(of(user)));
        }
        return usersDto;
    }
}
