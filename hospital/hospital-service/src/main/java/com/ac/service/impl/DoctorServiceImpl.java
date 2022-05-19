package com.ac.service.impl;

import com.ac.bean.Doctor;
import com.ac.dao.DoctorDao;
import com.ac.exception.LoginException;
import com.ac.service.DoctorService;
import com.ac.utils.MD5Util;
import com.ac.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DoctorServiceImpl
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/5 15:34
 * @Version 1.0
 **/
@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorDao doctorDao;


    @Override
    public boolean updateDoctorInfo(Map<String, String> mapUpdateData) {
        int num = doctorDao.updateDoctorInfo(mapUpdateData);
        if (num != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Map<String, Object> isLogin(String doctorId) {
        Map<String, Object> mapRet = new HashMap<>();
        mapRet.put("success", true);
        Map<String, Object> payload = new HashMap<String, Object>();
        Date date = new Date();
        payload.put("doctorId", doctorId);//用户ID
        payload.put("iat", date.getTime());//生成时间
        payload.put("ext", date.getTime() + 1000 * 60 * 60);//过期时间1小时
        String token = TokenUtil.createToken(payload);
        mapRet.put("token", token);
        return mapRet;
    }

    @Override
    public List<Doctor> searchDoctorInfoPage(int pageNum, int pageSize, String keyWord, int deptId) {
        Map<String, Object> map = new HashMap<>();
        int offset = (pageNum - 1) * pageSize;
        map.put("offset", offset);
        map.put("pageSize", pageSize);
        if (!"null".equals(keyWord)) {
            map.put("keyWord", keyWord);
        }
        if (deptId != 0) {
            map.put("deptId", deptId);
        }
        return doctorDao.searchDoctorInfoPage(map);
    }

    @Override
    public Map<String, Object> loginByUsername(Map<String, String> map) throws LoginException {
        map.put("doctorPassword", MD5Util.getMD5(map.get("doctorPassword")));
        Doctor doctor = doctorDao.loginByUsername(map);

        if (doctor == null) {
            throw new LoginException("账号或密码错误");
        }
        // 登录成功
        Map<String, Object> mapReturn = new HashMap<>();
        Map<String, Object> payload = new HashMap<String, Object>();
        Date date = new Date();
        payload.put("doctorId", doctor.getId());//医生ID
        payload.put("iat", date.getTime());//生成时间
        payload.put("ext", date.getTime() + 1000 * 60 * 60);//过期时间1小时
        String token = TokenUtil.createToken(payload);
        log.info("----------------token:" + token + "----------------");

        mapReturn.put("doctorId", doctor.getId());
        mapReturn.put("token", token);

        return mapReturn;
    }

    /**
     * 根据部门名称获取医生信息
     *
     * @param pageNum  页码
     * @param pageSize 页面大小
     * @param deptId   部门id
     * @return
     */
    @Override
    public List<Doctor> getDoctorInfoPageByDept(int pageNum, int pageSize, int deptId) {
        Map<String, Object> map = new HashMap<>();
        int offset = (pageNum - 1) * pageSize;
        map.put("offset", offset);
        map.put("pageSize", pageSize);
        map.put("departmentId", deptId);
        return doctorDao.getDoctorInfoPageByDept(map);
    }


    /**
     * 获取所有医生总数
     *
     * @return
     */
    @Override
    public int getAllDoctorNum() {
        return doctorDao.getAllDoctorNum();
    }


    /**
     * 获取当前医生信息
     *
     * @param id 医生id
     * @return
     */
    @Override
    public Doctor getThisDoctorInfo(int id) {
        Map<String, Object> map = new HashMap<>();
        log.info("id:" + id);
        map.put("id", id);
        Doctor doctor = doctorDao.getThisDoctorInfo(map);
        return doctor;
    }

    @Override
    public boolean deleteDoctor(int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);

        int num = doctorDao.deleteDoctor(map);
        if (num != 0) {
            return true;
        } else {
            return false;
        }
    }

}
