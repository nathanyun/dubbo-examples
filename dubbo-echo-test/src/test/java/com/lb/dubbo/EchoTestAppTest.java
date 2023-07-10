package com.lb.dubbo;

import com.lb.dubbo.service.GreetingService;
import org.apache.dubbo.rpc.service.EchoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:dubbo-consumer.xml")
public class EchoTestAppTest {

    @Autowired
    @Qualifier("greetingService")
    private GreetingService greetingService;

    @Test
    public void echoTest() {
        //指定获取 EchoService, 所有dubbo接口自动实现此接口, 用于回声测试
        EchoService echoService = (EchoService)greetingService;
        Object result = echoService.$echo("checkPass");
        System.out.println("EchoService.$echo result ===> " + result);
        Assert.assertEquals("result must be checkPass", "checkPass", result);
    }
}
