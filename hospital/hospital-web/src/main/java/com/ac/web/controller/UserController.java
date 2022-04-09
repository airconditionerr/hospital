package com.ac.web.controller;

import com.ac.bean.User;
import com.ac.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/1 17:21
 * @Version 1.0
 **/
@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     *
     * @param map 接收axios数据
     * @return
     */
    @PostMapping(value = "/user/login")
    @ResponseBody
    public Map<String, Object> loginByUsername(@RequestBody Map<String, String> map) {
        log.info("----------------进入用户登录操作----------------");
        log.info("----------------用户登录信息:" + map.toString() + "----------------");
        Map<String, Object> mapReturn = new HashMap<>();
        try {
            // service返回的数据：user | token
            Map<String, Object> map1 = userService.loginByUsername(map);
            // 返回的数据
            mapReturn.put("userId", map1.get("userId"));
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


    /**
     * 用户注册
     *
     * @param mapRegisterData 接收axios数据   {
     *                        "username",
     *                        "password",
     *                        "email",
     *                        "mobile",
     *                        "sex",
     *                        "name",
     *                        "idCard"}
     * @return mapReturn    {"success", "errorMsg"}
     */
    @PostMapping(value = "/user/register")
    @ResponseBody
    public Map<String, Object> registerByUsername(@RequestBody Map<String, String> mapRegisterData, HttpServletRequest request) {
        log.info("----------------进入注册操作----------------");
        mapRegisterData.put("registerIp", request.getRemoteAddr());
        log.info("----------------注册信息:" + mapRegisterData.toString() + "----------------");
        Map<String, Object> mapReturn = new HashMap<>();
        try {
            userService.registerByUsername(mapRegisterData);
            mapReturn.put("success", true);
        } catch (Exception e) {
            log.info("----------------注册失败----------------");
            log.info("----------------注册失败原因：" + e.getMessage() + "----------------");
            mapReturn.put("success", false);
            mapReturn.put("errorMsg", e.getMessage());
            return mapReturn;
        }
        return mapReturn;
    }


    /**
     * 发送验证码
     *
     * @param map   {"userEmail"}
     * @return vcode
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     */
    @PostMapping(value = "/user/vcode")
    @ResponseBody
    public String sendVcode(@RequestBody Map<String, String> map) throws UnsupportedEncodingException, MessagingException {
        log.info("----------------进入发送验证码操作----------------");
        String vcode = userService.sendRegisterVcode(map);
        log.info("----------------发送验证码成功----------------");
        return vcode;
    }


    /**
     * 判断用户是否登录
     *
     * @return mapRet {"success", "token"}
     */
    @RequestMapping(value = "/user/status/{userId}")
    @ResponseBody
    public Map<String, Object> isLogin(@PathVariable("userId") String userId) {
        Map<String, Object> mapRet = userService.isLogin(userId);
        return mapRet;
    }


    /**
     * 根据userId获取用户信息
     *
     * @param userId
     * @return User
     */
    @GetMapping(value = "/user/info/{userId}")
    @ResponseBody
    public User getUserInfoByUserId(@PathVariable("userId") String userId) {
        return userService.getUserInfoByUserId(userId);
    }


    /**
     * 获取一页用户信息
     *
     * @param pageNum  页码
     * @param pageSize 页面大小
     * @return list
     */
    @GetMapping(value = "/user/page/{pageNum}/{pageSize}")
    @ResponseBody
    public List<User> getUserInfoPage(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        log.info("----------------进入获取所有用户信息操作----------------");
        log.info(String.valueOf(pageNum));
        log.info(String.valueOf(pageSize));
        List<User> list = userService.getUserInfoPage(pageNum, pageSize);
        log.info("----------------获取所有用户信息成功----------------");
        return list;
    }


    /**
     * 删除用户
     *
     * @param userId
     * @return success
     */
    @DeleteMapping(value = "/user/{userId}")
    @ResponseBody
    public boolean deleteUser(@PathVariable("userId") int userId) {
        boolean success = userService.deleteUser(userId);
        return success;
    }


    /**
     * 更新用户信息
     *
     * @param mapUpdateData {"userId", "userEmail", "userMobile"}
     * @return
     */
    @PutMapping(value = "/user")
    @ResponseBody
    public boolean updateUserInfo(@RequestBody Map<String, String> mapUpdateData) {
        boolean success = userService.updateUserInfo(mapUpdateData);
        return success;
    }
}
