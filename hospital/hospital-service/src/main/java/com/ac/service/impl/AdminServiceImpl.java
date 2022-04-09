package com.ac.service.impl;

import com.ac.bean.Admin;
import com.ac.bean.User;
import com.ac.dao.AdminDao;
import com.ac.exception.LoginException;
import com.ac.service.AdminService;
import com.ac.utils.MD5Util;
import com.ac.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AdminServiceImpl
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/27 14:31
 * @Version 1.0
 **/
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;


    @Override
    public Map<String, Object> adminLoginByUsername(Map<String, String> map) throws LoginException {
        map.put("adminPassword", MD5Util.getMD5(map.get("adminPassword")));
        Admin admin = adminDao.adminLoginByUsername(map);

        if (admin == null) {
            throw new LoginException("账号或密码错误");
        }
        // 登录成功
        Map<String, Object> mapReturn = new HashMap<>();
        Map<String, Object> payload = new HashMap<String, Object>();
        Date date = new Date();
        payload.put("adminId", admin.getAdminId());//用户ID
        payload.put("iat", date.getTime());//生成时间
        payload.put("ext", date.getTime() + 1000 * 60 * 60);//过期时间1小时
        String token = TokenUtil.createToken(payload);
        log.info("----------------token:" + token + "----------------");

        mapReturn.put("admin", admin);
        mapReturn.put("token", token);

        return mapReturn;
    }

    @Override
    public Map<String, Object> isLogin(String adminId) {
        Map<String,Object> mapRet = new HashMap<>();
        mapRet.put("success", true);
        Map<String, Object> payload = new HashMap<String, Object>();
        Date date = new Date();
        payload.put("adminId", adminId);//用户ID
        payload.put("iat", date.getTime());//生成时间
        payload.put("ext", date.getTime() + 1000 * 60 * 60);//过期时间1小时
        String token = TokenUtil.createToken(payload);
        mapRet.put("token", token);
        return mapRet;
    }
}
