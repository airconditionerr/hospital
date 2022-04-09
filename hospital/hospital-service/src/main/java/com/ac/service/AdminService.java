package com.ac.service;

import com.ac.exception.LoginException;

import java.util.Map;

/**
 * @InterfaceName AdminService
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/27 14:31
 * @Version 1.0
 **/
public interface AdminService {
    Map<String, Object> adminLoginByUsername(Map<String, String> map) throws LoginException;

    Map<String, Object> isLogin(String adminId);
}
