package com.ac.service.impl;

import com.ac.bean.OrderRecord;
import com.ac.dao.OrderRecordDao;
import com.ac.service.OrderRecordService;
import com.ac.utils.DateUtil;
import com.ac.utils.MailUtil;
import com.ac.utils.TokenUtil;
import com.ac.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName OrderRecordServiceImpl
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/23 1:29
 * @Version 1.0
 **/
@Service
public class OrderRecordServiceImpl implements OrderRecordService {

    @Autowired
    private OrderRecordDao orderRecordDao;
    @Autowired
    private MailUtil mailUtil;


    @Override
    public boolean appoint(String token, Map<String, Object> mapAppointData) throws UnsupportedEncodingException, MessagingException {
        String userId = TokenUtil.getId(token);
        mapAppointData.put("orderId", UUIDUtil.getUUID());
        mapAppointData.put("userId", userId);
        mapAppointData.put("createTime", DateUtil.getCurrentDate());
        mapAppointData.put("isSuccess", 1);
        int num = orderRecordDao.appoint(mapAppointData);
        mailUtil.sendEmail("预约通知", "预约成功", (String) mapAppointData.get("email"));
        mapAppointData.put("notifyTime", DateUtil.getCurrentDate());
        mapAppointData.put("isSend", 1);
        orderRecordDao.notifyUser(mapAppointData);
        if (num != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<OrderRecord> getOrderInfoPage(int pageNum, int pageSize, String userid) {
        Map<String, Object> map = new HashMap<>();
        int offset = (pageNum - 1) * pageSize;
        map.put("offset", offset);
        map.put("pageSize", pageSize);
        map.put("userId", userid);
        List<OrderRecord> list = orderRecordDao.getOrderInfoPage(map);
        return list;
    }

    @Override
    public List<OrderRecord> getOrderInfoPage2(int pageNum, int pageSize) {
        Map<String, Object> map = new HashMap<>();
        int offset = (pageNum - 1) * pageSize;
        map.put("offset", offset);
        map.put("pageSize", pageSize);
        List<OrderRecord> list = orderRecordDao.getOrderInfoPage2(map);
        return list;
    }


    @Override
    public boolean finish(String orderId) {
        int num = orderRecordDao.finish(orderId);
        if (num != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getOrderNumByDoctorId(String doctorId) {
        Map<String, Object> map = new HashMap<>();
        map.put("doctorId", doctorId);
        int num = orderRecordDao.getOrderNumByDoctorId(map);
        return num;
    }

    @Override
    public int getOrderNumByUserId(String userId) {
        return orderRecordDao.getOrderNumByUserId(userId);
    }

    @Override
    public List<OrderRecord> getOrderInfoPageByDoctorId(int pageNum, int pageSize, String doctorId) {
        Map<String, Object> map = new HashMap<>();
        int offset = (pageNum - 1) * pageSize;
        map.put("offset", offset);
        map.put("pageSize", pageSize);
        map.put("doctorId", doctorId);
        List<OrderRecord> list = orderRecordDao.getOrderInfoPageByDoctorId(map);
        return list;
    }

    /**
     * 取消预约
     *
     * @param orderId
     * @return
     */
    @Override
    public boolean cancelAppoint(String orderId) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);

        int num = orderRecordDao.cancelAppoint(map);
        if (num != 0) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 获取预约信息
     *
     * @param orderId
     * @return
     */
    @Override
    public OrderRecord getOrderRecordInfo(String orderId) {
        OrderRecord orderRecord = orderRecordDao.getOrderRecordInfo(orderId);
        return orderRecord;
    }
}
