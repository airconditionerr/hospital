package com.ac.bean;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @ClassName HospitalDepartment
 * @Description TODO
 * @Author 29175
 * @Date 2022/2/25 16:09
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Component
public class HospitalDepartment {
    private int id;
    private String departmentName; //科室名称
    private String hospitalName;   //医院名称
    private String doctorNum;  //医生数量
    private String departmentHonor;    //科室荣誉
    private String departmentEquipment;    //科室设备
    private String departmentIntroduction; //科室相关
    private String departmentDiagnosisScope;  //科室诊疗范围
}
