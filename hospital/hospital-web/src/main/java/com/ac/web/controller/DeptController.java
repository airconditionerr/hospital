package com.ac.web.controller;

import com.ac.bean.HospitalDepartment;
import com.ac.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DeptController
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/22 14:11
 * @Version 1.0
 **/
@Controller
@Slf4j
public class DeptController {

    @Autowired
    private DeptService deptService;


    /**
     * 搜索/获取当前页科室信息
     *
     * @param pageNum
     * @param pageSize
     * @param keyWord
     * @return
     */
    @GetMapping(value = "/dept/page/{pageNum}/{pageSize}/{keyWord}")
    @ResponseBody
    public Map<String, Object> searchDeptInfoPage(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize, @PathVariable("keyWord") String keyWord) {
        log.info("----------------搜索科室----------------");
        log.info("pageNum:" + String.valueOf(pageNum) + ",pageSize:" + String.valueOf(pageSize) + ",keyWord:" + keyWord);
        List<HospitalDepartment> list = deptService.searchDeptInfoPage(pageNum, pageSize, keyWord);
        Map<String, Object> mapRet = new HashMap<>();
        mapRet.put("list", list);
        mapRet.put("totalNum", list.size());
        log.info("----------------搜索科室成功----------------");
        return mapRet;
    }


    /**
     * 获取科室总数
     *
     * @return
     */
    @GetMapping(value = "/dept/num")
    @ResponseBody
    public int getAllDeptNum() {
        log.info("----------------进入获取科室总数操作----------------");
        int num = deptService.getAllDeptNum();
        log.info("----------------获取科室总数成功----------------");
        return num;
    }


    /**
     * 获取当前科室信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/dept/info/{id}")
    @ResponseBody
    public HospitalDepartment getThisDeptInfo(@PathVariable("id") int id) {
        log.info("----------------进入获取当前科室信息操作----------------");
        HospitalDepartment hospitalDepartment = deptService.getThisDeptInfo(id);
        log.info("----------------获取当前科室信息成功----------------");
        return hospitalDepartment;
    }
}
