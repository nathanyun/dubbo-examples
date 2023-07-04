package com.lb.dubbo.service.impl;

import com.lb.dubbo.service.NotifyService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotifyServiceImpl implements NotifyService {

    @Override
    public void onInvoke(String name) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println(time + "  onInvoke name = " + name);
    }

    @Override
    public void onReturn(String result, String name) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println(time + "  onReturn result = " + result +", name = " + name);
    }

    @Override
    public void onThrow(Throwable ex, String name) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println(time + "  onThrow ex = " + ex +", name = " + name);
    }
}