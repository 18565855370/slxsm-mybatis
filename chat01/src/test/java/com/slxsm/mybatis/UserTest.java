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
public class UserTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws IOException {
        //指定mybatis全局配置文件
        String resource  = "mybatis-config.xml";
        //读取全局配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //构建sqlSessionFactory对象
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void sqlSession(){
        /*try(SqlSession session = sqlSessionFactory.openSession()){
            //创建UserModel对象
            UserModel userModel = UserModel.builder().id(2L).name("slxsm").age(30).salary(500000D).sex(1).build();
            //执行插入操作
            int result = session.insert("com.slxsm.mybatis.mapper.UserMapper.insertUser",userModel);
            log.info("插入影响的行数: {}",result);
            //提交事务
            session.commit();
        }*/
        /*try(SqlSession sqlSession = this.sqlSessionFactory.openSession(true)){
            //创建userModel对象
            UserModel userModel = UserModel.builder().id(2L).name("slxsm-zhang").age(30).salary(500000D).sex(1).build();
            int result = sqlSession.update("com.slxsm.mybatis.mapper.UserMapper.updateUser",userModel);
            log.info("影响的行数：{}",result);
        }*/

        /*try(SqlSession sqlSession = this.sqlSessionFactory.openSession(true)){
            Long userId = 1L;
            int result = sqlSession.delete("com.slxsm.mybatis.mapper.UserMapper.deleteUser",userId);
            log.info("影响的行数：{}",result);
        }*/

        /*try(SqlSession session = this.sqlSessionFactory.openSession(true)){
            List<UserModel> userModelList = session.selectList("com.slxsm.mybatis.mapper.UserMapper.getUserList");
            log.info("结果：{}",userModelList);
        }*/
        try(SqlSession sqlSession = this.sqlSessionFactory.openSession(true)){
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            UserModel userModel = UserModel.builder().id(System.currentTimeMillis()).name("路人甲Java").age(30).salary(50000D).sex(1).build();
            int result = userMapper.insertUser(userModel);
            log.info("影响行数：{}",result);
        }
    }
}
