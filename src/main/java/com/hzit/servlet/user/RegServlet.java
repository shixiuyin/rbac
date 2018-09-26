package com.hzit.servlet.user;

import com.hzit.bean.User;
import com.hzit.dao.UserDao;
import com.hzit.util.Md5;
import com.hzit.util.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RegServlet",urlPatterns = "/regServlet")
public class RegServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取参数
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //ID使用的UUID 自动生成
        String userId = UUIDUtils.getId();
        String loginName = request.getParameter("loginName");
        String loginPwd = Md5.getMd5String(request.getParameter("loginPwd")).toUpperCase();//MD5加密(登录时候也需要进行加密后在比对)
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        Integer status = Integer.parseInt(request.getParameter("status"));
        String remark = request.getParameter("remark");

        User user = new User(userId, loginName, loginPwd, userName, status, email, address, remark);
        //2.调用添加的方法
        UserDao userDao = new UserDao();
        int row = userDao.reg(user);
        //3.判断是否添加成功
        if(row>0)
        {
            response.sendRedirect(request.getContextPath()+"/jsp/Login.jsp");
        }else{
            HttpSession session = request.getSession();
            session.setAttribute("errorMsg","注册失败，请重新尝试!!");
            response.sendRedirect(request.getContextPath()+"/jsp/Register.jsp");
        }




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
