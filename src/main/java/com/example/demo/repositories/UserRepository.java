package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.dto.UserDto;

public interface UserRepository extends CrudRepository<UserDto, Long> {
    UserDto findByUsername(String username);
}