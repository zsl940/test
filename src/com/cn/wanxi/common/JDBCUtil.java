package com.cn.wanxi.common;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {

    private static Connection CONNECTION = null;
    private static PreparedStatement PREPARE = null;
    private static ResultSet resultSet = null;
    private static String URL;
    private static String USER;
    private static String PASSWORD;


    //使用properties加载属性文件
    static {
        Properties properties = new Properties();
        InputStream inputStream = JDBCUtil.class.getClassLoader()
                .getResourceAsStream("properties/jdbc.properties");
        try {
            assert inputStream != null;
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String driverClassName = properties.getProperty("driverClassName");
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        URL = properties.getProperty("url");
        USER = properties.getProperty("user");
        PASSWORD = properties.getProperty("password");
    }

    //获取数据库连接
    private static Connection getCONNECTION() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    //set sql.
    private static PreparedStatement getPREPARE(String sql, Object... objects) {
        CONNECTION = getCONNECTION();

        try {
            PREPARE = CONNECTION.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (objects.length > 0) {
            for (int i = 0; i < objects.length; i++) {
                try {
                    PREPARE.setObject(i + 1, objects[i]);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return PREPARE;
    }

    //增删改
    public static int upDate(String sql, Object... objects) {
        getCONNECTION();
        int result = 0;
        PreparedStatement statement = getPREPARE(sql, objects);
        try {
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //查
    public static ResultSet SelectDate(String sql, Object... objects) {
        getCONNECTION();
        PreparedStatement statement = getPREPARE(sql, objects);
        try {
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    //关闭数据库连接
    public static void ClosesConnection() {
        try {
            CONNECTION.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    upData("select * from myFrom where id < ?",5);
}