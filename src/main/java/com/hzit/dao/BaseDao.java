package com.hzit.dao;

import java.sql.*;

public class BaseDao {


    //1.获取连接
    public static Connection getConnection() {

        try {
            //1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");

            //2.获取连接
            String url = "jdbc:mysql://localhost:3306/rbac?characterEncoding=utf8&useSSL=true";
            String name = "root";
            String pwd = "root";
            Connection connection = DriverManager.getConnection(url, name, pwd);
            //...获取数据对象 变量数据集
            return connection;
        } catch (Exception e) {
            System.out.println("获取连接失败....");
            e.printStackTrace();
            return null;
        }
    }


    //2.关闭
    public static void closeAll(Connection connection, Statement statement, ResultSet resultSet) {


        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    //3.增删改的公共方法  param:可变参数
    public int baseExecuteUpdate(String sql, Object... params) {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            if (params != null) {
                //设置参数
                for (int i = 0; i < params.length; i++) {
                    statement.setObject((i + 1), params[i]);
                }
            }
            // 执行
            int row = statement.executeUpdate();
            return row;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("操作数据异常.....");
        }finally {
            closeAll(connection,statement,null);
        }
        return 0;
    }

}
