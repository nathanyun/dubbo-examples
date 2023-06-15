package com.lb.dubbo.service.impl;

import com.lb.dubbo.service.UserDTO;
import com.lb.dubbo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Override
    public void save(UserDTO userDTO) {
        log.info("Execute save start...userDTO: {}", userDTO);
    }

    @Override
    public void update(UserDTO userDTO) {
        log.info("Execute update start...userDTO: {}", userDTO);
    }

    @Override
    public void delete(long id, String operator) {
        log.info("Execute delete start...id: {}, operator: {}", id, operator);
    }
}
