package com.hzit.dao;

import com.hzit.bean.Role;
import com.hzit.bean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDao extends BaseDao {


    /**
     * 获取所有的role信息，包括设置用户指定的角色
     * @param userId
     * @return
     */
    public List<Object> findRoleListByUser(String userId) {

        List<Object> list = new ArrayList<>();

        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;

        String sql  = "SELECT * FROM sys_role sr LEFT JOIN (SELECT ROLE_ID checked FROM sys_user_role_ele WHERE user_id = ?) sur ON sr.ROLE_ID = sur.checked";

        try {
            statement = connection.prepareStatement(sql);

            statement.setString(1,userId);

            rs = statement.executeQuery();
            while(rs.next())
            {
                Role role = new Role();
                role.setRoleId(rs.getString("role_id"));
                role.setRoleName(rs.getString("role_name"));
                role.setRoleDesc(rs.getString("role_desc"));
                role.setRoleActive(rs.getString("role_active"));

                //是否选中状态
                String checked =  rs.getString("checked");
                checked =  checked==null?"unchecked":"checked"; //根据数据库数据再次封装，方便页面操作
                role.setChecked(checked);

                list.add(role);
            }
            return  list;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll(connection,statement,rs);
        }


        return null;
    }


    public int updateOrDeleteRole(String userId, String[] roleIds) {

        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false); //取消自动提交功能

            //1.根据userId 删除对应的数据
            delRoleByUserId(userId,connection);

            //2.根据userId 对roleIds的数据进行添加
            addRoleByUserId(userId,roleIds,connection);

            //3.提交
            connection.commit();
            return 1;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return 0;

        }finally {
            closeAll(connection,null,null);
        }

    }

    /**
     * 删除用户下所有的权限
     * @param userId
     * @return
     */
    private void delRoleByUserId(String userId,Connection connection) throws SQLException {

        String sql = "DELETE FROM sys_user_role_ele WHERE user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,userId);
        preparedStatement.executeUpdate(); //执行删除操作

    }


    private void addRoleByUserId(String userId,String [] roleIds,Connection connection) throws SQLException {
        for (String roleId:roleIds) {
             String sql = "INSERT INTO sys_user_role_ele VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userId);
            preparedStatement.setString(2,roleId);
            preparedStatement.executeUpdate(); //执行删除操作
        }
    }


    public List<Object> findRoleListByPage(Integer page, Integer limit) {
        List<Object> list = new ArrayList<>();

        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;

        String sql  = "SELECT * FROM sys_role LIMIT ? ,?";

        try {
            statement = connection.prepareStatement(sql);

            statement.setInt(1,(page-1)*limit);
            statement.setInt(2,limit);

            rs = statement.executeQuery();
            while(rs.next())
            {
                Role role = new Role();
                role.setRoleId(rs.getString("role_id"));
                role.setRoleName(rs.getString("role_name"));
                role.setRoleDesc(rs.getString("role_desc"));
                role.setRoleActive(rs.getString("role_active"));
                list.add(role);
            }
            return  list;

        } catch (SQLException e) {
            e.printStackTrace();
        }



        return null;
    }

    public Integer getRoleCount() {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;

        String sql  = "SELECT COUNT(1) cnt FROM sys_role";

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
        }finally {
            closeAll(connection,statement,rs);
        }

        return null;
    }
}
