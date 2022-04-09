package com.ac.dao;

import com.ac.bean.OrderRecord;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName OrderRecordDao
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/23 1:29
 * @Version 1.0
 **/
public interface OrderRecordDao {
    int appoint(Map<String,Object> map);

    List<OrderRecord> getOrderInfoPage(Map<String, Object> map);

    List<OrderRecord> getOrderInfoPage2(Map<String, Object> map);


    /**
     * 取消预约
     * @param map
     * @return
     */
    int cancelAppoint(Map<String,Object> map);

    void notifyUser(Map<String, Object> mapAppointData);

    OrderRecord getOrderRecordInfo(String orderId);

    List<OrderRecord> getOrderInfoPageByDoctorId(Map<String, Object> map);

    int finish(String orderId);

    int getOrderNumByDoctorName(Map<String, Object> map);
}
