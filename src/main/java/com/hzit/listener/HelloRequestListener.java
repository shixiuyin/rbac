package com.hzit.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class HelloRequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        System.out.println(servletRequestEvent.getServletRequest().getRemoteAddr()+"--->销毁");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        System.out.println(servletRequestEvent.getServletRequest().getRemoteAddr()+"--->发起了请求");
    }
}
