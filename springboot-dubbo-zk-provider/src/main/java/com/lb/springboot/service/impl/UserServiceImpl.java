package com.lb.springboot.service.impl;

import com.lb.springboot.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 功能描述:UserServiceImpl <br/>
 *
 * @author yunnasheng
 * @date: 2020-12-31 13:14<br/>
 * @since JDK 1.8
 */
@DubboService(version = "1.0")
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String get(String id) {
        logger.info("User get id: {}",id);
        return id;
    }
}
