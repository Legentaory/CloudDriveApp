package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.DAOs.UserDao;
import com.udacity.jwdnd.course1.cloudstorage.pojos.User;
import com.udacity.jwdnd.course1.cloudstorage.services.securityservices.HashService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    @Resource
    private UserDao userDao;
    @Resource
    private HashService hashService;

    public int getUserId(String username){
        return userDao.getUser(username).getUserId();
    }

    public String createUser(User user){
        String errorMsg = null;
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);

        user.setPassword(hashedPassword);
        user.setSalt(encodedSalt);


        if (userDao.getUser(user.getUsername()) != null){
            errorMsg = "The username has been taken, try another one.";
        } else {
            int userId = userDao.createUser(user);
            errorMsg = userId > 0? null: "System error, please try again later";
        }

        return errorMsg;
    }

}
