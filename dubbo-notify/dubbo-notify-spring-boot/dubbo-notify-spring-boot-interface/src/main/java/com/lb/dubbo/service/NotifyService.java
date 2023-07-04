package com.lb.dubbo.service;


/**
 * 回调接口
 */
public interface NotifyService {

    /**
     * 调用之前执行，如果被调用的服务有参数，那么oninvoke也必要有和被调用服务一样的参数
     * @param name
     */
    void onInvoke(String name);


    /**
     * 调用之后, 至少有一个入参，第一个入参是返回值，其余是调用服务的参数
     * onreturn(service返回值，service原参数1,service原参数2...)
     *
     * @param result
     * @param name
     */
    void onReturn(String result, String name);

    /**
     * 出现异常时,至少一个参数，类型为被抛出服务异常的父类或其本身，其余是调用服务的参数
     * onthrow(Throwable ex，service原参数1,service原参数2...)
     * @param ex
     * @param name
     */
    void onThrow(Throwable ex, String name);

}