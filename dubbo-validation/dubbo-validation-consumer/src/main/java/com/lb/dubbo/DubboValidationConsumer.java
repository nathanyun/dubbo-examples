package com.lb.dubbo;
import java.util.Date;

import com.lb.dubbo.service.UserDTO;
import com.lb.dubbo.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.validation.ValidationException;
import java.io.IOException;

public class DubboValidationConsumer {

    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-consumer.xml");
        context.start();

        UserService userService = context.getBean(UserService.class);

        // Save OK
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Jack");
        userDTO.setEmail("jack@qq.com");
        userDTO.setAge(18);
        userDTO.setPastDate(new Date(System.currentTimeMillis() - 1000000));
        userDTO.setFutureDate(new Date(System.currentTimeMillis() + 1000000));
        userService.save(userDTO);


        // Save Error
        try {
            userDTO = new UserDTO();
            userService.save(userDTO);
        } catch (ValidationException e) {
            System.err.println("Validation save ERROR");
            e.printStackTrace();
        }

        // Delete OK
        userService.delete(2, "abc");
        System.out.println("Validation Delete OK");

        // Delete Error
        try {
            userService.delete(0, "abc");
        } catch (ValidationException e) {
            System.err.println("Validation Delete ERROR");
            e.printStackTrace();
        }
        //阻塞主线程
        //System.in.read();
    }
}
