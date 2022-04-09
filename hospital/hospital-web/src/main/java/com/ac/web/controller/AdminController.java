package com.ac.web.controller;

import com.ac.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AdminController
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/27 14:31
 * @Version 1.0
 **/
@Controller
@Slf4j
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/admin/login")
    @ResponseBody
    public Map<String, Object> loginByUsername(@RequestBody Map<String, String> map) {
        log.info("----------------进入管理员登录操作----------------");
        log.info("----------------管理员登录信息:" + map.toString() + "----------------");
        Map<String, Object> mapReturn = new HashMap<>();
        try {
            // service返回的数据：admin | token
            Map<String, Object> map1 = adminService.adminLoginByUsername(map);
            // 返回的数据
            mapReturn.put("admin", map1.get("admin"));
            mapReturn.put("token", map1.get("token"));
            mapReturn.put("success", true);
        } catch (Exception e) {
            log.info("----------------登录失败----------------");
            log.info("----------------失败原因：" + e.getMessage() + "----------------");
            // 返回的数据
            mapReturn.put("errorMsg", e.getMessage());
            mapReturn.put("success", false);
            return mapReturn;
        }
        log.info("----------------登录成功----------------");
        return mapReturn;
    }


    @RequestMapping(value = "/admin/status/{adminId}")
    @ResponseBody
    public Map<String,Object> isLogin(@PathVariable("adminId")String adminId) {
        Map<String,Object> mapRet = adminService.isLogin(adminId);
        return mapRet;
    }
}
