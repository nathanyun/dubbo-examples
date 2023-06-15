package com.lb.dubbo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 执行前先启动provider服务
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/dubbo-consumer.xml"})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void saveOkTest(){
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Jack");
        userDTO.setEmail("jack@qq.com");
        userDTO.setAge(18);
        userDTO.setPastDate(new Date(System.currentTimeMillis() - 1000000));
        userDTO.setFutureDate(new Date(System.currentTimeMillis() + 1000000));
        userService.save(userDTO);
    }

    @Test
    public void saveFailTest(){
        UserDTO userDTO = new UserDTO();
        userService.save(userDTO);
    }

    @Test
    public void betweenTest(){
        LocalDateTime createTime = LocalDateTime.now();
        LocalDateTime delayTime = createTime.plusHours(2).plusSeconds(1);
        System.out.println("seconds = " + Duration.between(createTime, delayTime).getSeconds());
    }
}
