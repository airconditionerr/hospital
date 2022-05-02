package com.ac.service;

import com.ac.bean.OrderRecord;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * @InterfaceName OrderRecordService
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/23 1:29
 * @Version 1.0
 **/
public interface OrderRecordService {
    boolean appoint(String token, Map<String, Object> mapAppointData) throws UnsupportedEncodingException, MessagingException;

    List<OrderRecord> getOrderInfoPage(int pageNum, int pageSize, String userid);

    List<OrderRecord> getOrderInfoPage2(int pageNum, int pageSize);


    /**
     * 取消预约
     * @param orderId
     * @return
     */
    boolean cancelAppoint(String orderId);


    /**
     * 获取预约信息
     * @param orderId
     * @return
     */
    OrderRecord getOrderRecordInfo(String orderId);

    List<OrderRecord> getOrderInfoPageByDoctorId(int pageNum, int pageSize, String doctorId);

    boolean finish(String orderId);

    int getOrderNumByDoctorId(String doctorId);
}
