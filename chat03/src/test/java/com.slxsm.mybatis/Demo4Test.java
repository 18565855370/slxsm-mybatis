package com.slxsm.mybatis;

import com.slxsm.mybatis.domain.UserFindDTO;
import com.slxsm.mybatis.domain.UserModel;
import com.slxsm.mybatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.result.DefaultResultContext;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class Demo4Test {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Test
    public void test(){
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            UserModel userModel = userMapper.getByName("slxsm");
            log.info("{}",userModel);
        }
    }

    @Test
    public void test1(){
        try(SqlSession sqlSession = this.sqlSessionFactory.openSession(true)){
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Map<String, Object> map = new HashMap<>(2);
            map.put("id",1);
            map.put("name","slxsm");
            List<UserModel> userModel = userMapper.getByMap(map);
            log.info("{}",userModel);
        }
    }

    @Test
    public void getListByFindDto(){
        try(SqlSession sqlSession = this.sqlSessionFactory.openSession(true)){
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            UserFindDTO userFindDTO = UserFindDTO.builder().userId(1L).userName("slxsm").build();
            List<UserModel> userModelList = userMapper.getListByUserFindDTO(userFindDTO);
            userModelList.forEach(item -> {
                log.info("{}",item);
            });
        }
    }

    @Test
    public void getByIdOrNameTest(){
        try(SqlSession sqlSession = this.sqlSessionFactory.openSession(true)){
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<UserModel> userModel = userMapper.getByIdOrName(1L,"slxsm");
            log.info("{}",userModel);
        }
    }

    @Test
    public void getListByUserFindDTOTest(){
        try(SqlSession sqlSession = this.sqlSessionFactory.openSession(true)){
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            UserModel userModel = userMapper.getListByUserFindDTO(1L,"slxsm");
            log.info("{}",userModel);
        }
    }

    @Test
    public void getListByIdCollection(){
        log.info("-------------------");
        try(SqlSession sqlSession = this.sqlSessionFactory.openSession(true)){
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<Long> userIdList = Arrays.asList(1L,3L);
            List<UserModel> userModelList = userMapper.getListByIdCollection(userIdList);
            userModelList.forEach(item -> {
                log.info("{}",item);
            });
        }
    }

    @Test
    public void getListTest(){
        log.info("----------------");
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.getList(resultContext -> {
                //将context参数转换为DefaultResultCOntext对象
                DefaultResultContext<UserModel> defaultResultContext = (DefaultResultContext<UserModel>) resultContext;
                log.info("{}",defaultResultContext.getResultCount());
                //遍历到第二条停止
                if (defaultResultContext.getResultCount() == 2){
                    //调用stop方法停止遍历，stop方法会更新内部的一个标志位，置为停止遍历
                    defaultResultContext.stop();
                }
            });
        }
    }


}
