package com.moyuan.cloud.security.entity;

import lombok.Data;


/**
 * @author shuang.kou
 */
@Data
public class LoginUser {

    private String username;
    private String password;
    private String phonenumber;
    private String smscode;
    private Boolean rememberMe;
}
