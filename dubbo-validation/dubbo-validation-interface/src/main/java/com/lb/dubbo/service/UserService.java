package com.lb.dubbo.service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

// 缺省可按服务接口区分验证场景，如：@NotNull(groups = ValidationService.class)
public interface UserService {

    void save(UserDTO userDTO);

    void update(UserDTO userDTO);

    void delete(@Min(1) long id, @NotNull @Size(min = 2, max = 16) @Pattern(regexp = "^[a-zA-Z]+$") String operator);

    /**
     * 与方法同名接口，首字母大写，用于区分验证场景，如：@NotNull(groups = ValidationService.Save.class)，可选
     */
    @interface Save {
    }

    /**
     * annotation which has the same name with the method but has the first letter in capital
     * used for distinguish validation scenario, for example: @NotNull(groups = ValidationService.Update.class)
     * optional
     */
    @interface Update {
    }
}
