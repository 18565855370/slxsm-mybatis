package com.slxsm.mybatis.mapper;

import com.slxsm.mybatis.domain.UserModel;

import java.util.List;

/**
 * @author slxsm
 * @date 2020/1/11
 */
public interface UserMapper {

    int insertUser(UserModel model);

    int updateUser(UserModel model);

    int deleteUser(Long userId);

    List<UserModel> getUserList();
}
