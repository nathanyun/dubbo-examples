# 回声测试 
客户端发送一个包含特定值（如字符串）的请求。服务器应使用相同的值进行响应，从而验证请求是否已成功接收和处理。如果响应与请求不匹配，则表示服务运行不正常。

## 使用场景
测试验证是否可以调用服务以及响应是否正确，对于在尝试在生产环境中使用服务之前验证服务特别有用。

## 使用方式
所有服务自动实现 EchoService 接口，只需将任意服务引用强制转型为 EchoService，即可使用。
```java
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
```

## 官方手册
https://cn.dubbo.apache.org/en/docs3-v2/java-sdk/advanced-features-and-usage/service/echo-service/