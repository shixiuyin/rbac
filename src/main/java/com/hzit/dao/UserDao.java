package com.hzit.dao;

import com.hzit.bean.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends BaseDao {


    /**
     * 根据登录名称 获取所有的数据
     *
     * @param loginName
     * @return 用户信息
     */
    public User userLogin(String loginName) {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;

        String sql  = "SELECT * FROM sys_user WHERE login_name = ?";

        try {
             statement = connection.prepareStatement(sql);
             statement.setString(1,loginName);

             rs = statement.executeQuery();
            if(rs.next())
            {
                User user = new User();
                user.setUserId(rs.getString("USER_ID"));
                user.setUserName(rs.getString("USER_NAME"));
                user.setLoginName(rs.getString("LOGIN_NAME"));
                user.setLoginPwd(rs.getString("LOGIN_PWD"));
                user.setStatus(rs.getInt("STATUS")); //获取第一个字符
                user.setEmail(rs.getString("EMAIL"));
                user.setAddress(rs.getString("ADDRESS"));
                user.setRemark(rs.getString("REMARK"));

                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }


    /**
     * 查询user列表
     * @return
     */
    public List<Object> findUserListByPage() {

        List<Object> list = new ArrayList<>();

        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;

        String sql  = "SELECT * FROM sys_user";

        try {
            statement = connection.prepareStatement(sql);

            rs = statement.executeQuery();
            while(rs.next())
            {
                User user = new User();
                user.setUserId(rs.getString("USER_ID"));
                user.setUserName(rs.getString("USER_NAME"));
                user.setLoginName(rs.getString("LOGIN_NAME"));
                user.setLoginPwd(rs.getString("LOGIN_PWD"));
                user.setStatus(rs.getInt("STATUS")); //获取第一个字符
                user.setEmail(rs.getString("EMAIL"));
                user.setAddress(rs.getString("ADDRESS"));
                user.setRemark(rs.getString("REMARK"));

                list.add(user);
            }
            return  list;

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }


    /**
     * 获取用户的总数据量
     * @return
     */
    public Integer getUserCount() {


        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;

        String sql  = "SELECT COUNT(1) cnt FROM sys_user";

        try {
            statement = connection.prepareStatement(sql);

            rs = statement.executeQuery();
            if(rs.next())
            {
                int cnt = rs.getInt("cnt");
                return cnt;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



    /**
     * 用户注册
     * @param user
     * @return
     */
    public int reg(User user) {
        String sql = "INSERT INTO sys_user(user_id,login_name,login_pwd,user_name,status,email,address,remark) values(?,?,?,?,?,?,?,?)";
        int row = baseExecuteUpdate(sql, user.getUserId(), user.getLoginName(), user.getLoginPwd(), user.getUserName(), user.getStatus(), user.getEmail(), user.getAddress(), user.getRemark());
        return  row;
    }
}
