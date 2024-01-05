package com.security.web3.mapper;

import com.security.web3.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void createUser(UserVo user);

    UserVo getUserById(long id);

    UserVo getUserByUsername(String username);

    int updateUser(UserVo userVo);

    int deleteUser(long id);

    int countDuplicateUsername(Long id, String username);

    int countDuplicateEmail(Long id, String email);
}
