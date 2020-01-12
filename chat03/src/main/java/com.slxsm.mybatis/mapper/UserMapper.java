package com.slxsm.mybatis.mapper;

import com.slxsm.mybatis.domain.UserFindDTO;
import com.slxsm.mybatis.domain.UserModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ResultHandler;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author slxsm
 * @date 2020/1/11
 */
public interface UserMapper {

    UserModel getByName(String name);

    List<UserModel> getByMap(Map<String, Object> map);

    List<UserModel> getListByUserFindDTO(UserFindDTO userFindDTO);

    List<UserModel> getByIdOrName(Long id, String name);

    UserModel getListByUserFindDTO(@Param("userId") Long id, @Param("userName") String name);

    List<UserModel> getListByIdCollection(Collection<Long> idCollection);

    void getList(ResultHandler<UserModel> resultHandler);
}
