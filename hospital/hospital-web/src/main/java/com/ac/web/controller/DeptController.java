package com.ac.web.controller;

import com.ac.bean.HospitalDepartment;
import com.ac.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
     * 获取当前页科室信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/dept/page/{pageNum}/{pageSize}")
    @ResponseBody
    public List<HospitalDepartment> getDeptInfoPage(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        log.info("----------------进入获取当前页科室信息操作----------------");
        log.info(String.valueOf(pageNum));
        log.info(String.valueOf(pageSize));
        List<HospitalDepartment> list = deptService.getDeptInfoPage(pageNum, pageSize);
        log.info("----------------获取当前页科室信息成功----------------");
        return list;
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
