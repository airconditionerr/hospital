package com.ac.service;

import com.ac.bean.Doctor;
import com.ac.exception.LoginException;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName DoctorService
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/5 15:34
 * @Version 1.0
 **/
public interface DoctorService {
    List<Doctor> getDoctorInfoPage(int pageNum, int pageSize);

    List<Doctor> getDoctorInfoPageByDept(int pageNum, int pageSize, String deptName);

    int getAllDoctorNum();

    Doctor getThisDoctorInfo(int id);

    boolean deleteDoctor(int id);

    boolean updateDoctorInfo(Map<String, String> mapUpdateData);

    Map<String, Object> loginByUsername(Map<String, String> map) throws LoginException;

    Map<String, Object> isLogin(String doctorId);
}
