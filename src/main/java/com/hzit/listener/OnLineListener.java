package com.hzit.listener;

import com.hzit.bean.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * 监听在线用户
 */
public class OnLineListener implements HttpSessionAttributeListener, HttpSessionListener {

    private List<User> onlineList;

    /**
     * 监听添加
     *
     * @param httpSessionBindingEvent
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        // 当往session域中添加 userInfo的时候，表示登录成功,在线
        //key
        String name = httpSessionBindingEvent.getName();

        ServletContext application = httpSessionBindingEvent.getSession().getServletContext();

        if ("userInfo".equals(name)) {


            User userInfo = (User) httpSessionBindingEvent.getValue();

            //监听到往 session域中添加了一个 userInfo的属性（只有登录成功之后才添加）

            //从application域中获取list容器
            Object obj = application.getAttribute("onlineList");
            if (obj == null) {
                onlineList = new ArrayList<>();
            } else {
                onlineList = (List<User>) obj;
            }
            onlineList.add(userInfo);

            //存放到applicaiton域中
            application.setAttribute("onlineList", onlineList);
        }


    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    /**
     * 销毁
     *
     * @param httpSessionEvent
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {


        //sessionId
        String id = httpSessionEvent.getSession().getId();

        //判断该session 是否在线  如果删除掉
        ServletContext application = httpSessionEvent.getSession().getServletContext();
        Object obj = application.getAttribute("onlineList");
        if (obj != null) {
            List<User> onlineList = (List<User>) obj;

            for (User user : onlineList) {
                if (user.getSessionId().equals(id)) {
                    onlineList.remove(user);//移除
                    break;
                }
            }
        }

    }
}
