package com.slxsm.chat04.mybatis.mapper;

import com.slxsm.chat04.mybatis.domain.UserModel;

public interface UserMapper {

    /**
     * 插入用户信息，返回影响行数
     * @param model
     * @return
     */
    int insertUser(UserModel model);

    /**
     * 更行用户数据，返回影响用户行数
     * @param model
     * @return
     */
    int updateUser(UserModel model);

    /**
     * 根据用户id删除用户信息
     * @param userId
     * @return
     */
    boolean deleteUser(Long userId);

}
