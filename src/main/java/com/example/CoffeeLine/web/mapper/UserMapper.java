package com.example.CoffeeLine.web.mapper;

import com.example.CoffeeLine.common.Role;
import com.example.CoffeeLine.domain.User;
import com.example.CoffeeLine.dto.user.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
//    @Mapping(target = "roles", expression = "roles")
    UserResponseDto toUserResponseDto(User user);

    default String mapRolesToString(Set<Role> roles) {
        if (roles == null) {
            return null;
        }

        return roles.stream()
                .map(Enum::name)
                .collect(Collectors.joining(", "));
    }
}
