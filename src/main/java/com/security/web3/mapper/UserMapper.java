package com.security.web3.mapper;

import com.security.web3.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void createUser(UserVo user);

    UserVo getUserByUsername(String username);

    int updateUser(UserVo userVo);

    int deleteUser(String username);

    int countDuplicateUsername(String username);

    int countDuplicateEmail(String username, String email);

    String getEncodedPassword(String username);
}
