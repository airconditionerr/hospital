package com.ac.service;

import com.ac.bean.HospitalDepartment;

import java.util.List;

/**
 * @InterfaceName DeptService
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/22 14:12
 * @Version 1.0
 **/
public interface DeptService {
    List<HospitalDepartment> getDeptInfoPage(int pageNum, int pageSize);

    int getAllDeptNum();

    HospitalDepartment getThisDeptInfo(int id);
}
