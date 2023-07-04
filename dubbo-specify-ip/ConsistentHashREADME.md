## 基本原理
一致性哈希选址, dubbo底层采用`环形队列hash映射`模型实现. 将服务按照ip+端口按照一定规则做MD5, 然后把MD5值对 2^32取模,确定服务在Hash值区间对应的位置。

## 使用场景
- 在分布式系统中跨多个节点均匀分布请求的方法，使用哈希算法创建请求的哈希并根据哈希值确定哪个节点应该处理请求，算法确保每个节点处理的请求数量大致相等。
- 如果一个节点发生故障，其他节点可以快速接管请求，保持系统高可用性，即使一个节点出现故障，系统的数据映射到系统中有限数量节点的哈希算法，在系统中添加或删除节点时，只需更改有限数量的映射，确保数据均匀分布在系统中的所有节点上提高系统的性能。
- 在有多台服务端的时候根据请求参数的进行一致性哈希散列选择服务端。


## 使用方式
- 注解方式
    ```java  
    @DubboReference(loadbalance = “consistenthash”)  
    ```
- API方式
    ```java  
    referenceConfig.setLoadBalance(“consistenthash”);
    ```
- properties方式
    ```properties  
    dubbo.reference.loadbalance=consistenthash
    ```
- properties方式
    ```xml  
    <dubbo:reference loadbalance=“consistenthash” />
    ```

## 案例说明
1. 依次启动 `Provider1` ,`Provider2`, `Provider3` 
2. 然后启动 `ConsistentHashConsumer1`, `ConsistentHashConsumer2`, `ConsistentHashConsumer3` (消费者每隔2秒进行1次远程调用)
3. 观察提供者日志
4. 手动停止任意 `Provider`, 继续观察提供者日志

## 官方手册
https://cn.dubbo.apache.org/en/docs3-v2/java-sdk/advanced-features-and-usage/service/consistent-hash/