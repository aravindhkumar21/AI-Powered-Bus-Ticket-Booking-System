package com.project.BusTicketBooking.mapper;

import com.project.BusTicketBooking.dto.user.UserRequestDTO;
import com.project.BusTicketBooking.dto.user.UserResponseDTO;
import com.project.BusTicketBooking.model.User;

public class UserMapper {

    // RequestDTO -> Entity
    public static User toEntity(UserRequestDTO dto) {

        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setPassword(dto.getPassword());

        return user;
    }

    // Entity -> ResponseDTO
    public static UserResponseDTO toResponseDTO(User user) {

        UserResponseDTO dto = new UserResponseDTO();

        dto.setUserId(user.getUserId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());

        return dto;
    }

    // Update Entity
    public static void updateEntity(User user, UserRequestDTO dto) {

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(dto.getPassword());
        }
    }
}
