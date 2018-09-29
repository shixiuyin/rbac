package com.hzit.dao;

import com.hzit.bean.Menu;
import com.hzit.bean.Nav;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDao extends BaseDao {


    /**
     * 根据角色获取对应的权限信息(全部的权限|和被选中的权限)
     *
     * @param roleId
     * @return
     */
    public List<Menu> findMenuListByRoleId(String roleId) {


        String sql = "SELECT * FROM(SELECT sm.MENU_ID mId,sm.MENU_NAME mName, sm2.* from  sys_menu sm LEFT JOIN  sys_menu sm2 ON sm.menu_id = sm2.parent_id WHERE sm.PARENT_ID = '0') sm4 LEFT JOIN (SELECT MENU_ID checked FROM sys_role_menu_ele WHERE ROLE_ID = ?) sm3  ON sm4.MENU_ID = sm3.checked";

        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, roleId);


            ResultSet rs = preparedStatement.executeQuery();


            //存放父节点的菜单
            List<Menu> menuList = new ArrayList<>();


            while (rs.next()) {

                if (menuList.size() == 0) {
                    Menu menu = new Menu(); //父节点的信息  里面包含了有子节点

                    menu.setMenuId(rs.getString("mId"));
                    menu.setMenuName(rs.getString("mName"));

                    List<Menu> subMenuList = new ArrayList<>();
                    Menu subMenu = new Menu();
                    subMenu.setMenuId(rs.getString("menu_id"));
                    subMenu.setMenuName(rs.getString("menu_name"));
                    subMenu.setLinkUrl(rs.getString("link_url"));
                    subMenu.setStatus(rs.getString("status"));
                    subMenu.setIcon(rs.getString("icon"));
                    subMenu.setRemark(rs.getString("remark"));

                    String checked = rs.getString("checked");
                    subMenu.setChecked(checked == null ? "unchecked" : "checked");

                    subMenuList.add(subMenu);
                    menu.setSubMenu(subMenuList);

                    menuList.add(menu);
                } else {


                    String pId = rs.getString("mId");
                    Menu menu = isExist(menuList, pId); //判断是否存在指定的菜单

                    // menu = menu==null?new Menu():menu;
                    if (menu != null) { //只需要添加子菜单
                        //从数据查询出来
                        Menu isMenu = new Menu();
                        isMenu.setParentId(rs.getString("parent_id")); //父ID
                        isMenu.setMenuId(rs.getString("menu_id"));
                        isMenu.setMenuName(rs.getString("menu_name"));
                        isMenu.setLinkUrl(rs.getString("link_url"));
                        isMenu.setStatus(rs.getString("status"));
                        isMenu.setIcon(rs.getString("icon"));
                        isMenu.setRemark(rs.getString("remark"));
                        String checked = rs.getString("checked");
                        isMenu.setChecked(checked == null ? "unchecked" : "checked");

                        List<Menu> subMenuList = menu.getSubMenu(); //获取菜单下所有子菜单
                        subMenuList.add(isMenu); //当前新的子菜单添加到原来的子菜单中
                    } else { //重新一个新的节点,并且添加子节点

                        Menu menu2 = new Menu(); //父节点的信息  里面包含了有子节点
                        menu2.setMenuId(rs.getString("mId"));
                        menu2.setMenuName(rs.getString("mName"));

                        List<Menu> subMenuList = new ArrayList<>();
                        Menu subMenu = new Menu();
                        subMenu.setMenuId(rs.getString("menu_id"));
                        subMenu.setMenuName(rs.getString("menu_name"));
                        subMenu.setLinkUrl(rs.getString("link_url"));
                        subMenu.setStatus(rs.getString("status"));
                        subMenu.setIcon(rs.getString("icon"));
                        subMenu.setRemark(rs.getString("remark"));

                        String checked = rs.getString("checked");
                        subMenu.setChecked(checked == null ? "unchecked" : "checked");

                        subMenuList.add(subMenu);
                        menu2.setSubMenu(subMenuList);

                        menuList.add(menu2);

                    }
                }

                //取值  一条记录 ：父节点信息 子节点信息  是否被选中


            }

            return menuList;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 判断集合是否存在menu对象
     *
     * @param menuList
     * @param mId
     * @return
     */
    private Menu isExist(List<Menu> menuList, String mId) {
        for (Menu menu : menuList) {

            if (mId.equals(menu.getMenuId())) {
                return menu;
            }
        }
        return null;
    }


    public static void main(String[] args) {

        List<Menu> menuList = new MenuDao().findMenuListByRoleId("0");

        System.out.println(menuList);

    }


    /**
     * 根据参数 维护  角色--资源菜单的关系
     * @param roleId
     * @param menuIds
     * @return
     */
    public int updateOrDeleteMenuByRoleId(String roleId, String[] menuIds) {

        //1.删除该角色所有的内容
        Connection connection = getConnection();
        //手动提交事务
        try {
            connection.setAutoCommit(false);

            deleteMenuByRoleId(connection,roleId);

            addMenuToRoleId(connection,roleId,menuIds);

            //2.将所有的 menuIds 添加到关系表

            connection.commit();

            return 1;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            closeAll(connection,null,null);
        }

        return 0;

    }


    /**
     * 删除
     * @param connection
     * @param roleId
     * @throws SQLException
     */
    private void deleteMenuByRoleId(Connection connection,String roleId) throws SQLException {
        String sql = "DELETE FROM sys_role_menu_ele WHERE ROLE_ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,roleId);
        preparedStatement.executeUpdate();
    }


    private void addMenuToRoleId(Connection connection,String roleId,String[] menuIds) throws SQLException {
        for (String menuId: menuIds) {
            String sql  = "INSERT INTO sys_role_menu_ele VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,roleId);
            preparedStatement.setString(2,menuId);
            preparedStatement.executeUpdate();
        }
    }


    public List<Nav> findNavByRoleId(String userId) {


       String sql = "SELECT p.menu_id pid,p.MENU_NAME pname,p.icon picon,s.* FROM(SELECT sm.* FROM (SELECT * FROM sys_role_menu_ele WHERE role_id IN (SELECT role_id FROM sys_user_role_ele WHERE user_id = ?)) srm  INNER JOIN sys_menu sm ON srm.menu_id = sm.MENU_ID ) s ,sys_menu p WHERE p.menu_id = s.parent_id";
        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userId);


            ResultSet rs = preparedStatement.executeQuery();


            //存放父节点的菜单
            List<Nav> navList = new ArrayList<>();


            while (rs.next()) {

                if (navList.size() == 0) {
                    //Menu menu = new Menu(); //父节点的信息  里面包含了有子节点

                    Nav nav = new Nav();
                    nav.setText(rs.getString("pname"));
                    nav.setIcon(rs.getString("picon"));



                    List<Nav> subMenuList = new ArrayList<>();

                    Nav subNav = new Nav();
                    subNav.setText(rs.getString("menu_name"));
                    subNav.setIcon(rs.getString("icon"));
                    subNav.setHref(rs.getString("link_url"));


                    subMenuList.add(subNav);
                    nav.setSubset(subMenuList);

                    navList.add(nav);
                } else {


                    String pname = rs.getString("pname");
                    //Menu menu = isExist(menuList, pId); //判断是否存在指定的菜单
                    Nav nav =  isNavExist(navList,pname);
                    // menu = menu==null?new Menu():menu;
                    if (nav != null) { //只需要添加子菜单
                        //从数据查询出来
                        Nav subNav = new Nav();
                        subNav.setText(rs.getString("menu_name"));
                        subNav.setIcon(rs.getString("icon"));
                        subNav.setHref(rs.getString("link_url"));

                        List<Nav> navSubset = nav.getSubset(); //获取菜单下所有子菜单
                        navSubset.add(subNav); //当前新的子菜单添加到原来的子菜单中
                    } else { //重新一个新的节点,并且添加子节点

                        Nav nav2 = new Nav();
                        nav2.setText(rs.getString("pname"));
                        nav2.setIcon(rs.getString("picon"));



                        List<Nav> subMenuList = new ArrayList<>();

                        Nav subNav = new Nav();
                        subNav.setText(rs.getString("menu_name"));
                        subNav.setIcon(rs.getString("icon"));
                        subNav.setHref(rs.getString("link_url"));


                        subMenuList.add(subNav);
                        nav2.setSubset(subMenuList);
                        navList.add(nav2);
                    }
                }

                //取值  一条记录 ：父节点信息 子节点信息  是否被选中


            }


            return navList;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 判断是否存在菜单
     * @param navList
     * @param pname
     * @return
     */
    private Nav isNavExist(List<Nav> navList, String pname) {
        for (Nav nav:navList) {

            if(pname.equals(nav.getText()))
            {
                return nav;
            }
        }
        return null;
    }



}
