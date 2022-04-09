package com.ac.dao;

import com.ac.bean.Admin;

import java.util.Map;

/**
 * @InterfaceName AdminDao
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/27 14:32
 * @Version 1.0
 **/
public interface AdminDao {
    Admin adminLoginByUsername(Map<String, String> map);
}
