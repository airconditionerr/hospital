package com.ac.web.controller;

import com.ac.bean.Doctor;
import com.ac.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DoctorController
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/5 15:33
 * @Version 1.0
 **/
@Controller
@Slf4j
public class DoctorController {

    @Autowired
    private DoctorService doctorService;


    /**
     * 医生登录
     *
     * @param map 接收axios数据
     * @return
     */
    @PostMapping(value = "/doctor/login")
    @ResponseBody
    public Map<String, Object> loginByUsername(@RequestBody Map<String, String> map) {
        log.info("----------------进入医生登录操作----------------");
        log.info("----------------医生登录信息:" + map.toString() + "----------------");
        Map<String, Object> mapReturn = new HashMap<>();
        try {
            // service返回的数据：doctor | token
            Map<String, Object> map1 = doctorService.loginByUsername(map);
            // 返回的数据
            mapReturn.put("doctorId", map1.get("doctorId"));
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
     * 获取/搜索当前页医生信息
     *
     * @param pageNum  页码
     * @param pageSize 页面大小
     * @param keyWord 关键词
     * @param deptId 科室id
     * @return
     */
    @GetMapping(value = "/doctor/page/{pageNum}/{pageSize}/{keyWord}/{deptId}")
    @ResponseBody
    public Map<String, Object> searchDoctorInfoPage(@PathVariable("pageNum") int pageNum,
                                                    @PathVariable("pageSize") int pageSize,
                                                    @PathVariable("keyWord") String keyWord,
                                                    @PathVariable("deptId") int deptId) {
        log.info("----------------搜索当前页医生信息操作----------------");
        log.info("----------------pageNum:" + String.valueOf(pageNum) + ",pageSize:" + String.valueOf(pageSize) + ",keyWord：" + keyWord + ",deptId:" + String.valueOf(deptId) + "----------------");
        List<Doctor> list = doctorService.searchDoctorInfoPage(pageNum, pageSize, keyWord, deptId);
        Map<String, Object> mapRet = new HashMap<>();
        mapRet.put("list", list);
        mapRet.put("totalNum", list.size());
        log.info("----------------搜索当前页医生信息成功----------------");
        return mapRet;
    }


    /**
     * 获取所有医生总数
     *
     * @return
     */
    @GetMapping(value = "/doctor/num")
    @ResponseBody
    public int getAllDoctorNum() {
        log.info("----------------进入获取所有医生数量操作----------------");
        int num = doctorService.getAllDoctorNum();
        log.info("----------------获取所有医生数量成功----------------");
        return num;
    }

    /**
     * 更新医生信息
     * @param mapUpdateData
     * @return
     */
    @PutMapping(value = "/doctor/info")
    @ResponseBody
    public boolean updateDoctorInfo(@RequestBody Map<String, String> mapUpdateData) {
        boolean success = doctorService.updateDoctorInfo(mapUpdateData);
        return success;
    }


    /**
     * 获取当前医生信息
     *
     * @param id 医生id
     * @return
     */
    @GetMapping(value = "/doctor/info/{id}")
    @ResponseBody
    public Doctor getThisDoctorInfo(@PathVariable("id") int id) {
        log.info("----------------进入获取当前医生信息操作----------------");
        Doctor doctor = doctorService.getThisDoctorInfo(id);
        log.info("----------------获取当前医生信息成功----------------");
        return doctor;
    }

    /**
     * 删除医生
     * @param id    医生id
     * @return
     */
    @DeleteMapping(value = "/doctor/{id}")
    @ResponseBody
    public boolean deleteDoctor(@PathVariable("id") int id) {
        boolean success = doctorService.deleteDoctor(id);
        return success;
    }







    @RequestMapping(value = "/doctor/status/{doctorId}")
    @ResponseBody
    public Map<String, Object> isLogin(@PathVariable("doctorId") String doctorId) {
        Map<String, Object> mapRet = doctorService.isLogin(doctorId);
        return mapRet;
    }


}
