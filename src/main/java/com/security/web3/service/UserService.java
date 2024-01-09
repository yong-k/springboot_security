package com.security.web3.service;

import com.security.web3.exception.DataNotFoundException;
import com.security.web3.mapper.UserMapper;
import com.security.web3.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void createUser(UserVo user) {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userMapper.createUser(user);
    }

    public UserVo getUserByUsername(String username) {
        UserVo user = userMapper.getUserByUsername(username);
        if (username == null)
            throw new DataNotFoundException("Not exist user: username = " + username);
        return user;
    }

    public void updateUser(UserVo user) {
        if (!user.getPassword().isEmpty()) {
            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
        }
        int result = userMapper.updateUser(user);
        if (result < 1)
            throw new DataNotFoundException("[UPDATE fail] Not exist user: username = " + user.getUsername());
    }

    public void deleteUser(String username) {
        int result = userMapper.deleteUser(username);
        if (result < 1)
            throw new DataNotFoundException("[DELETE fail] Not exist user: username = " + username);
    }

    public int countDuplicateUsername(String username) {
        return userMapper.countDuplicateUsername(username);
    }

    public int countDuplicateEmail(String username, String email) {
        return userMapper.countDuplicateEmail(username, email);
    }

    public boolean isMatchPassword(String username, String inputPassword) {
        String encodedPassword = userMapper.getEncodedPassword(username);
        return bCryptPasswordEncoder.matches(inputPassword, encodedPassword);
    }
}
