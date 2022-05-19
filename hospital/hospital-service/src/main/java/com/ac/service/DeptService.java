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
    int getAllDeptNum();

    HospitalDepartment getThisDeptInfo(int id);

    List<HospitalDepartment> searchDeptInfoPage(int pageNum, int pageSize, String keyWord);
}
