package com.hzit.servlet.user;

import com.hzit.bean.User;
import com.hzit.dao.UserDao;
import com.hzit.util.Md5;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "LoginServlet", urlPatterns = "/loginServlet")
public class LoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();

        String errorMsg = null;

        //1.获取参数
        String loginName = request.getParameter("loginName");
        String loginPwd = request.getParameter("loginPwd");

        //2.根据名称查找数据库
        UserDao userDao = new UserDao();
        User user = userDao.userLogin(loginName);

        //3.验证 保存数据到session域

        //使用MD5加密  不可逆的
        loginPwd = Md5.getMd5String(loginPwd).toUpperCase();//加密

        if(user==null)
        {
            errorMsg = "当前用户名不存在!!!";
            session.setAttribute("errorMsg",errorMsg);
            //用户名或者密码错误
            response.sendRedirect(request.getContextPath()+"/jsp/Login.jsp");

        }else {

            if(!loginPwd.equals(user.getLoginPwd()))
            {
                errorMsg = "当前用户名或者密码不匹配!!!";
                session.setAttribute("errorMsg",errorMsg);
                //用户名或者密码错误
                response.sendRedirect(request.getContextPath()+"/jsp/Login.jsp");
            }else
            {

                //补缺user 信息，统计在线用户
                String sessionId =  session.getId()==null?"":session.getId();

                String ip = request.getRemoteAddr();
                String logintime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                user.setSessionId(sessionId);
                user.setIp(ip);
                user.setLoginTime(logintime);

                //登录成功  保存到域对象中
                session.setAttribute("userInfo",user);
                session.removeAttribute("errorMsg");
                //重定向到主界面
                response.sendRedirect(request.getContextPath()+"/index.jsp");
            }

        }





    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}
