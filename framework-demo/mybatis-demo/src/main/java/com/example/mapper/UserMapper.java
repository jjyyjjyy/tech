package com.example.mapper;

import com.example.domain.User;

import java.util.List;

/**
 * @author jy
 */
public interface UserMapper {

    List<User> findAll();
}
