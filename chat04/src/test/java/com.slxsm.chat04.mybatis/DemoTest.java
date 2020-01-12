package com.slxsm.chat04.mybatis;

import com.slxsm.chat04.mybatis.domain.UserModel;
import com.slxsm.chat04.mybatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class DemoTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Test
    public void insertTest(){
        try(SqlSession sqlSession = this.sqlSessionFactory.openSession(true)){
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            UserModel userModel = UserModel.builder().id(1L).name("slxsm").age(18).salary(5000D).sex(0).build();
            int result = userMapper.insertUser(userModel);
            log.info("{}",result);
        }
    }

    @Test
    public void updateTest(){
        try(SqlSession sqlSession = this.sqlSessionFactory.openSession(true)){
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            UserModel userModel = UserModel.builder().id(1L).name("slxsm-love-qll").age(30).salary(1000D).sex(1).build();
            int result = userMapper.updateUser(userModel);
            log.info("{}",result);
        }
    }

    @Test
    public void deleteUser(){
        try(SqlSession sqlSession = this.sqlSessionFactory.openSession(true)){
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Long userId = 1L;
            boolean result = userMapper.deleteUser(userId);
            log.info("第一次删除：id = {}. 返回值：{}",userId,result);
            result = userMapper.deleteUser(userId);
            log.info("第二次删除：id = {}. 返回值：{}",userId,result);
        }
    }
}
