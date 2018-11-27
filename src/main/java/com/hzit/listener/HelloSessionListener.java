package com.hzit.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 监听session对象的创建和销毁
 */
public class HelloSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        //1.没打开一个新的会话都会创建

        System.out.println("来了一个session:id="+httpSessionEvent.getSession().getId());


    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        //1.销毁

        //a.调用失效的方法  b.超时
        System.out.println("销毁session:id="+httpSessionEvent.getSession().getId());

    }
}
