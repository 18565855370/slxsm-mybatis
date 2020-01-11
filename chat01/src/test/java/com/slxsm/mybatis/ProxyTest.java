package com.slxsm.mybatis;

import com.slxsm.mybatis.domain.UserModel;
import com.slxsm.mybatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author slxsm
 * @date 2020/1/11
 */
@Slf4j
public class ProxyTest {

    public static class UserMapperProxy implements InvocationHandler {

        private SqlSession sqlSession;

        private Class<?> mapperClass;

        public UserMapperProxy(SqlSession sqlSession, Class<?> mapperClass){
            this.sqlSession = sqlSession;
            this.mapperClass = mapperClass;
        }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            log.debug("invoke start");
            String statement = mapperClass.getName() + "." + method.getName();
            List<Object> result = sqlSession.selectList(statement);
            log.debug("invoke end");
            return result;
        }
    }

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws IOException{
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        this.sqlSessionFactory = sessionFactory;
    }

    @Test
    public void test() {
        try(SqlSession sqlSession = this.sqlSessionFactory.openSession(true)){
            UserModel userModel = UserModel.builder().id(2L).name("slxsm-zhang").age(30).salary(500000D).sex(1).build();

            UserMapper userMapper =
                    (UserMapper) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(),new Class[]{UserMapper.class},new UserMapperProxy(sqlSession,UserMapper.class));
            log.info("{}",userMapper.insertUser(userModel));
        }
    }
}
