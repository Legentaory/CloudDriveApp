package com.udacity.jwdnd.course1.cloudstorage.DAOs;

import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.pojos.User;
import com.udacity.jwdnd.course1.cloudstorage.services.securityservices.HashService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserDao {

    @Resource
    private UserMapper userMapper;

    public int createUser(User user) {
        return userMapper.insert(user);
    }

    public User getUser(String username) {
        return userMapper.getUser(username);
    }

    public User getUser(Integer userId) {
        return userMapper.getUserById(userId);
    }
}
