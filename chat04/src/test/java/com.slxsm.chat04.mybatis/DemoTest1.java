package com.slxsm.chat04.mybatis;

import com.slxsm.chat04.mybatis.domain.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.sql.*;

@Slf4j
public class DemoTest1 {

    private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    private String jdbcUrl = "jdbc:mysql://47.93.195.126:3306/slxsm_mybatis_test?characterEncoding=UTF-8";
    private String jdbcUserName = "test";
    private String jdbcPassword = "123456";

    @Test
    public void jdbcInsertUser() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try{
            UserModel userModel = UserModel.builder().name("qll").age(28).salary(7000D).sex(0).build();
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword);
            preparedStatement =
                    connection.prepareStatement("INSERT INTO t_user (name,age,salary,sex) VALUES (?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);
            int parameterIndex = 1;
            preparedStatement.setString(parameterIndex++,userModel.getName());
            preparedStatement.setInt(parameterIndex++,userModel.getAge());
            preparedStatement.setDouble(parameterIndex++, userModel.getSalary());
            preparedStatement.setInt(parameterIndex++, userModel.getSex());
            int count = preparedStatement.executeUpdate();
            //int count = preparedStatement.executeUpdate("SELECT LAST_INSERT_ID()");
            log.info("影响的行数: {}",count);
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys != null && generatedKeys.next()){
                log.info("自增值为：{}",generatedKeys.getInt(1));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            if (generatedKeys != null && generatedKeys.isClosed()) {
                generatedKeys.close();
            }
            if (preparedStatement != null && preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            if (connection != null && connection.isClosed()) {
                connection.close();
            }
        }
    }
}
