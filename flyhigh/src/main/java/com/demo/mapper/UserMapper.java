package com.demo.mapper;

import com.demo.dto.FlightDTO;
import com.demo.dto.UserDTO;
import com.demo.entities.Flight;
import com.demo.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserDTO userDTO);
}
