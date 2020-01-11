package com.slxsm.mybatis;

import com.slxsm.mybatis.domain.UserModel;
import com.slxsm.mybatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author slxsm
 * @date 2020/1/11
 */
@Slf4j
public class UserMapperTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        this.sqlSessionFactory = sessionFactory;
    }

    @Test
    public void getUserList(){
        try(SqlSession sqlSession = this.sqlSessionFactory.openSession(true)){
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<UserModel> userModelList = userMapper.getUserList();
            userModelList.forEach(item -> {
                log.info("{}",item);
            });
        }
    }
}
