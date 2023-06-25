package com.lb.dubbo;

import com.lb.dubbo.service.GreetingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:dubbo-consumer.xml")
public class DowngradeAppTest {

    @Autowired
    @Qualifier("greetingService")
    private GreetingService greetingService;

    @Test
    public void testMock() {
        String response = greetingService.sayHi("world");
        System.out.println("response = " + response);
    }
}
