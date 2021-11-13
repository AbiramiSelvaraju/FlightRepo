package com.demo.services;

import com.demo.dto.UserDTO;
import com.demo.entities.Airline;
import com.demo.entities.User;
import com.demo.mapper.UserMapper;
import com.demo.repositories.AirlineRepository;
import com.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private UserMapper mapper;

    public List<User> getAllUsers(){
        return repo.findAll();
    }

    public void createUser(UserDTO userDTO) {
        User user = mapper.toUser(userDTO);
        repo.save(user);
    }

}
