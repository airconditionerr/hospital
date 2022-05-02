package com.ac.bean;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @ClassName Doctor
 * @Description TODO
 * @Author 29175
 * @Date 2022/2/25 16:10
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Component
public class Doctor {
    private int id;
    private String doctorName;
    private String doctorIntroduction; //相关
    private String doctorSex;  //性别
    private String doctorImg;  //照片
    private String doctorPosition; //职称
    private String doctorDegree;   //学位
    private String doctorHospital; //所在医院
    private String doctorForte;    //特长
    private String teacherTitle;   //教学职称
    private String doctorAccount;
    private String doctorPassword;
    private String doctorDepartmentId;   //科室ID
}
