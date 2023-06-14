package com.lb.dubbo.service.impl;

import com.lb.dubbo.service.MergeService;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.ArrayList;
import java.util.List;

@DubboService(group = "merge")
public class MergeServiceImpl1 implements MergeService {
    @Override
    public List<String> mergeResult() {
        List<String> menus = new ArrayList<>();
        menus.add("group-1.1");
        menus.add("group-1.2");
        return menus;
    }
}
