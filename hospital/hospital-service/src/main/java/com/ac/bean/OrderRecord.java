package com.ac.bean;

import lombok.*;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * @ClassName OrderRecord
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
public class OrderRecord {
    private String orderId;
    private String userId;
    private String hospitalName;   //医院名称
    private String departmentName; //科室名称
    private String doctorName; //医生姓名
    private String transactDate;   //预约办理日期
    private String transactTime;   //预约办理时间段
    private String diseaseInfo;    //疾病信息
    private int isSuccess;  //是否预约成功
    private int isSend; //是否发送通知成功
    private int isCancel;   //是否取消
    private Timestamp createTime;
    private int orderVer;   //预约识别码
    private int isFinish;   //是否完成
    private String notifyTime;
}
