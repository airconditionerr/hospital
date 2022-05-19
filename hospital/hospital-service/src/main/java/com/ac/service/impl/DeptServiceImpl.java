package com.ac.service.impl;

import com.ac.bean.HospitalDepartment;
import com.ac.dao.DeptDao;
import com.ac.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DeptServiceImpl
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/22 14:12
 * @Version 1.0
 **/
@Service
@Slf4j
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;


    /**
     * 获取科室总数
     *
     * @return
     */
    @Override
    public int getAllDeptNum() {
        int num = deptDao.getAllDeptNum();
        return num;
    }


    /**
     * 获取当前科室信息
     *
     * @param id
     * @return
     */
    @Override
    public HospitalDepartment getThisDeptInfo(int id) {
        Map<String, Object> map = new HashMap<>();
        log.info("id:" + id);
        map.put("id", id);
        HospitalDepartment hospitalDepartment = deptDao.getThisDeptInfo(map);
        return hospitalDepartment;
    }


    @Override
    public List<HospitalDepartment> searchDeptInfoPage(int pageNum, int pageSize, String keyWord) {
        Map<String,Object> map = new HashMap<>();
        int offset = (pageNum - 1) * pageSize;
        map.put("pageSize", pageSize);
        map.put("offset", offset);
        if (!"null".equals(keyWord)){
            map.put("keyWord", keyWord);
        }
        return deptDao.searchDeptInfoPage(map);
    }
}
