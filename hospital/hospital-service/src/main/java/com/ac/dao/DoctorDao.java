package com.ac.dao;

import com.ac.bean.Doctor;

import java.util.List;
import java.util.Map;

/**
 * @ClassName DoctorDao
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/5 15:34
 * @Version 1.0
 **/
public interface DoctorDao {
    List<Doctor> getDoctorInfoPage(Map<String, Object> map);

    int getAllDoctorNum();

    Doctor getThisDoctorInfo(Map<String, Object> map);

    List<Doctor> getDoctorInfoPageByDept(Map<String, Object> map);

    int deleteDoctor(Map<String, Object> map);

    int updateDoctorInfo(Map<String, String> mapUpdateData);

    Doctor loginByUsername(Map<String, String> map);
}
