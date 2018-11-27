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
    public List<Object> findUserListByPage(Integer page,Integer limit) {

        List<Object> list = new ArrayList<>();

        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;

        String sql  = "SELECT * FROM sys_user LIMIT ? ,?";

        try {
            statement = connection.prepareStatement(sql);

            statement.setInt(1,(page-1)*limit);
            statement.setInt(2,limit);

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
        String sql = "INSERT INTO sys_user(login_name,login_pwd,user_name,status,email,address,remark) values(?,?,?,?,?,?,?)";
        int row = baseExecuteUpdate(sql, user.getLoginName(), user.getLoginPwd(), user.getUserName(), user.getStatus(), user.getEmail(), user.getAddress(), user.getRemark());
        return  row;
    }

    /**
     * 根据ID删除
     * @param userIds
     * @return
     */
    public int delUserById(String[] userIds) {

        //拼接字符串
        String where = "";
        for (int i = 0;i<userIds.length;i++)
        {
            if(i==userIds.length-1){
                where = where+"?";
            }else
            {
                where = where+"?,";
            }
        }
        //拼接sql
        String sql = "DELETE FROM sys_user WHERE user_id in("+where+")";
        System.out.println("delete_sql:"+sql);

        //调用删除的方法
        int row = baseExecuteUpdate(sql, userIds);

        return row;
    }

    /**
     * 根据用户ID  修改用户的状态
     * @param userId
     * @return
     */
    public int stopAccount(String userId,Integer status) {
        String sql = "UPDATE sys_user SET status=? WHERE user_id=?";
        return  baseExecuteUpdate(sql,status,userId);
    }
}
