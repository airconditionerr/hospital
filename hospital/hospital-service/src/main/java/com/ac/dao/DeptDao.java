package com.ac.dao;

import com.ac.bean.HospitalDepartment;

import java.util.List;
import java.util.Map;

/**
 * @ClassName DoctorDao
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/5 15:34
 * @Version 1.0
 **/
public interface DeptDao {
    List<HospitalDepartment> getDeptInfoPage(Map<String, Object> map);

    int getAllDeptNum();

    HospitalDepartment getThisDeptInfo(Map<String, Object> map);

    String getDepartmentNameByDeptId(Map<String, Object> map1);
}
