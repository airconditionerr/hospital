package com.ac.service.impl;

import com.ac.bean.User;
import com.ac.dao.UserDao;
import com.ac.exception.LoginException;
import com.ac.exception.RegisterException;
import com.ac.service.UserService;
import com.ac.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/1 22:59
 * @Version 1.0
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private MailUtil mailUtil;


    /**
     * 用户登录
     *
     * @param map {"username", "password"}
     * @return mapReturn {"userId", "token"}
     * @throws LoginException
     */
    @Override
    public Map<String, Object> loginByUsername(Map<String, String> map) throws LoginException {
        map.put("password", MD5Util.getMD5(map.get("password")));
        User user = userDao.loginByUsername(map);

        if (user == null) {
            throw new LoginException("账号或密码错误");
        }
        // 账号锁定状态
        if (user.getStatus() == 0) {
            throw new LoginException("账号已被锁定");
        }
        // 登录成功
        Map<String, Object> mapReturn = new HashMap<>();
        Map<String, Object> payload = new HashMap<String, Object>();
        Date date = new Date();
        payload.put("userId", user.getUserId());//用户ID
        payload.put("iat", date.getTime());//生成时间
        payload.put("ext", date.getTime() + 1000 * 60 * 60);//过期时间1小时
        String token = TokenUtil.createToken(payload);
        log.info("----------------token:" + token + "----------------");

        mapReturn.put("userId", user.getUserId());
        mapReturn.put("token", token);

        return mapReturn;
    }


    /**
     * 用户注册
     *
     * @param mapRegisterData {"userId",
     *                        "username",
     *                        "password",
     *                        "email",
     *                        "mobile",
     *                        "registerTime",
     *                        "registerIp",
     *                        "name",
     *                        "idCard"}
     * @throws RegisterException
     */
    @Override
    public void registerByUsername(Map<String, String> mapRegisterData) throws RegisterException {
        if (userDao.isExist(mapRegisterData.get("username")) == null) {
            // 用户名不存在
            // 用户密码MD5加密
            mapRegisterData.put("password", MD5Util.getMD5(mapRegisterData.get("password")));
            // userId
            mapRegisterData.put("userId", UUIDUtil.getUUID());
            // 注册时间
            mapRegisterData.put("registerTime", DateUtil.getCurrentDate());
            userDao.registerByUsername(mapRegisterData);
        } else {
            // 用户名已经存在
            throw new RegisterException("用户名已存在");
        }
    }


    /**
     * 发送验证码
     *
     * @param map
     * @return 验证码
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     */
    @Override
    public String sendRegisterVcode(Map<String, String> map) throws UnsupportedEncodingException, MessagingException {
        String vcode = String.valueOf((int) ((Math.random() * 9000) + 1000));
        mailUtil.sendEmail("Verification Code", vcode, map.get("email"));
        return vcode;
    }


    /**
     * 判断是否登录
     *
     * @param userId
     * @return mapRet {"success", "token"}
     */
    @Override
    public Map<String, Object> isLogin(String userId) {
        Map<String, Object> mapRet = new HashMap<>();
        mapRet.put("success", true);
        Map<String, Object> payload = new HashMap<String, Object>();
        Date date = new Date();
        payload.put("userId", userId);//用户ID
        payload.put("iat", date.getTime());//生成时间
        payload.put("ext", date.getTime() + 1000 * 60 * 60);//过期时间1小时
        String token = TokenUtil.createToken(payload);
        mapRet.put("token", token);
        return mapRet;
    }


    /**
     * 根据userId查询用户信息
     *
     * @param userId 用户id
     * @return User
     */
    @Override
    public User getUserInfoByUserId(String userId) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        return userDao.getUserInfoByUserId(map);
    }


    /**
     * 获取一页用户信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<User> getUserInfoPage(int pageNum, int pageSize) {
        Map<String, Object> map = new HashMap<>();
        int offset = (pageNum - 1) * pageSize;
        map.put("offset", offset);
        map.put("pageSize", pageSize);
        List<User> list = userDao.getUserInfoPage(map);
        return list;
    }


    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    @Override
    public boolean deleteUser(int userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        int num = userDao.deleteUser(map);
        if (num != 0) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 更新用户信息
     *
     * @param mapUpdateData {"userId", "userEmail", "userMobile"}
     * @return
     */
    @Override
    public boolean updateUserInfo(Map<String, String> mapUpdateData) {
        int num = userDao.updateUserInfo(mapUpdateData);
        if (num != 0) {
            return true;
        } else {
            return false;
        }
    }
}
