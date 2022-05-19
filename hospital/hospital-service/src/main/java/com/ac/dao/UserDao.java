package com.ac.dao;


import com.ac.bean.User;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName UserDao
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/1 22:59
 * @Version 1.0
 **/
public interface UserDao {
    /**
     * 用户登录
     *
     * @param map
     * @return
     */
    User loginByUsername(Map<String, String> map);

    /**
     * 用户注册
     *
     * @param mapRegisterData
     * @return
     */
    int registerByUsername(Map<String, String> mapRegisterData);


    /**
     * 判断用户是否存在
     *
     * @param username
     * @return
     */
    User isExist(String username);

    /**
     * 根据userId获取用户信息
     *
     * @param map
     * @return
     */
    User getUserInfoByUserId(Map<String, String> map);


    /**
     * 删除用户
     *
     * @param map
     * @return
     */
    int deleteUser(Map<String, Object> map);


    /**
     * 更新用户信息
     *
     * @param mapUpdateData {"userId", "userEmail", "userMobile"}
     * @return
     */
    int updateUserInfo(Map<String, String> mapUpdateData);

    int getUserNum();

    List<User> searchUserInfoPage(Map<String, Object> map);
}
