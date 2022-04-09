package com.ac.service;

import com.ac.bean.User;
import com.ac.exception.LoginException;
import com.ac.exception.RegisterException;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * @InterfaceName UserService
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/1 22:59
 * @Version 1.0
 **/
public interface UserService {
    /**
     * 用户登录
     *
     * @param map
     * @return
     * @throws LoginException
     */
    Map<String, Object> loginByUsername(Map<String, String> map) throws LoginException;


    /**
     * 用户注册
     *
     * @param mapRegisterData
     * @throws RegisterException
     */
    void registerByUsername(Map<String, String> mapRegisterData) throws RegisterException;


    /**
     * 发送验证码
     *
     * @param map   {"userEmail"}
     * @return
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     */
    String sendRegisterVcode(Map<String, String> map) throws UnsupportedEncodingException, MessagingException;


    /**
     * 判断用户是否登录
     *
     * @param userId
     * @return
     */
    Map<String, Object> isLogin(String userId);


    /**
     * 根据userId获取用户信息
     *
     * @param userId
     * @return
     */
    User getUserInfoByUserId(String userId);


    /**
     * 获取一页用户信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<User> getUserInfoPage(int pageNum, int pageSize);


    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    boolean deleteUser(int userId);


    /**
     * 更新用户信息
     *
     * @param mapUpdateData {"userId", "userEmail", "userMobile"}
     * @return
     */
    boolean updateUserInfo(Map<String, String> mapUpdateData);
}
