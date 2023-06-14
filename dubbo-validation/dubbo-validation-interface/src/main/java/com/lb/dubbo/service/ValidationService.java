package com.lb.dubbo.service;

// 缺省可按服务接口区分验证场景，如：@NotNull(groups = ValidationService.class)
public interface ValidationService {
    @interface Save{} // 与方法同名接口，首字母大写，用于区分验证场景，如：@NotNull(groups = ValidationService.Save.class)，可选
    void save(ValidationParam param);

    void update(ValidationParam param);
}
