package com.ac.bean;

import lombok.*;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @ClassName User
 * @Description TODO
 * @Author 29175
 * @Date 2022/2/25 16:09
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Component
public class User {
    private int id; //自增主键
    private String userId; //id
    private String name;    // 姓名
    private String idCard;  // 身份证
    private String userName;   //用户名
    private String userSex;    //性别
    private String userPassword;   //密码
    private String userEmail;  //邮箱
    private String userMobile; //电话
    private Timestamp userRegisterTime;   //注册时间
    private String userRegisterIp;    //注册ip
    private Date lastLoginTime;   //最后登录时间
    private String lastLoginIp;   //最后登录ip
    private Date updateTime;   //更新时间（激活邮件时间计时用）
    private int verificationCode;  //验证码
    private int status; //用户是否激活
    private int isDelete;  //用户是否存在
}
