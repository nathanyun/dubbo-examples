package com.lb.dubbo.service.impl;

import com.lb.dubbo.service.MergeService;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.ArrayList;
import java.util.List;

@DubboService(group = "merge2")
public class MergeServiceImpl2 implements MergeService {
    @Override
    public List<String> mergeResult() {
        List<String> menus = new ArrayList<>();
        menus.add("group-2.1");
        menus.add("group-2.2");
        return menus;
    }
}
