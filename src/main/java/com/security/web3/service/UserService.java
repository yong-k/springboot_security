package com.security.web3.service;

import com.security.web3.exception.DataNotFoundException;
import com.security.web3.mapper.UserMapper;
import com.security.web3.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createUser(UserVo user) {
        userMapper.createUser(user);
    }

    public UserVo getUserById(long id) {
        UserVo user = userMapper.getUserById(id);
        if (user == null)
            throw new DataNotFoundException("Not exist user: id = " + id);
        return user;
    }

    public UserVo getUserByUsername(String username) {
        UserVo user = userMapper.getUserByUsername(username);
        if (username == null)
            throw new DataNotFoundException("Not exist user: username = " + username);
        return user;
    }

    public void updateUser(UserVo user) {
        int result = userMapper.updateUser(user);
        if (result < 1)
            throw new DataNotFoundException("[UPDATE fail] Not exist user: id = " + user.getId());
    }

    public void deleteUser(long id) {
        int result = userMapper.deleteUser(id);
        if (result < 1)
            throw new DataNotFoundException("[DELETE fail] Not exist user: id = " + id);
    }
}
