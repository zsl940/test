package com.cn.wanxi.common;

import java.sql.*;

public class JDBC {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306" +
            "/market?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "qwe123456";
    private static ResultSet resultSet = null;
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;

    private JDBC() {
    }

    /**
     * 数据库的连接
     *
     * @return 一个数据库的链接
     */
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 数据库的增删改
     */

    public static void upDate(String sql) {
        getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeConnenction();
        }
    }

    /**
     * 返回查询结果集
     *
     * @param sql
     * @return
     */
    public static ResultSet getResultSet(String sql) {
        getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    private static void closeConnenction() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}