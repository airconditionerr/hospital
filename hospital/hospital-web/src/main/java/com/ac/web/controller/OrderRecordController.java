package com.ac.web.controller;

import com.ac.bean.OrderRecord;
import com.ac.service.OrderRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName OrderRecordController
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/23 1:25
 * @Version 1.0
 **/
@Controller
@Slf4j
public class OrderRecordController {

    @Autowired
    OrderRecordService orderRecordService;

    @PostMapping(value = "/orderRecord")
    @ResponseBody
    public boolean appoint(@RequestBody Map<String, Object> mapAppointData, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
        log.info("----------------进入预约操作----------------");
        log.info("----------------预约信息：" + mapAppointData.toString() + "----------------");
        String token = request.getHeader("token");
        boolean success = orderRecordService.appoint(token, mapAppointData);
        log.info("----------------预约完成----------------");
        return success;
    }

    @GetMapping(value = "/orderRecord/{pageNum}/{pageSize}/{userid}")
    @ResponseBody
    public List<OrderRecord> getOrderInfoPage(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize, @PathVariable("userid") String userid) {
        log.info("----------------进入获取所有订单信息操作----------------");
        log.info(String.valueOf(pageNum));
        log.info(String.valueOf(pageSize));
        log.info(userid);
        List<OrderRecord> list = orderRecordService.getOrderInfoPage(pageNum, pageSize, userid);
        log.info("----------------获取所有订单信息成功----------------");
        return list;
    }


    @GetMapping(value = "/orderRecord/page/{pageNum}/{pageSize}/{doctorName}")
    @ResponseBody
    public List<OrderRecord> getOrderInfoPageByDoctorName(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize, @PathVariable("doctorName") String doctorName) {
        log.info("----------------进入获取所有订单信息操作----------------");
        log.info(String.valueOf(pageNum)+ ", " + String.valueOf(pageSize) + ", " + doctorName);
        List<OrderRecord> list = orderRecordService.getOrderInfoPageByDoctorName(pageNum, pageSize, doctorName);
        log.info("----------------获取所有订单信息成功----------------");
        return list;
    }


    @GetMapping(value = "/orderRecord/num/{doctorName}")
    @ResponseBody
    public int getOrderNumByDoctorName(@PathVariable("doctorName") String doctorName) {
        log.info("----------------进入获取所有订单信息操作----------------");
        int num = orderRecordService.getOrderNumByDoctorName(doctorName);
        log.info("----------------获取所有订单信息成功----------------");
        return num;
    }


    @GetMapping(value = "/orderRecord/page/{pageNum}/{pageSize}")
    @ResponseBody
    public List<OrderRecord> getOrderInfoPage(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        log.info("----------------进入获取所有订单信息操作----------------");
        log.info(String.valueOf(pageNum));
        log.info(String.valueOf(pageSize));
        List<OrderRecord> list = orderRecordService.getOrderInfoPage2(pageNum, pageSize);
        log.info("----------------获取所有订单信息成功----------------");
        return list;
    }


    /**
     * 取消预约
     *
     * @param orderId
     * @return
     */
    @DeleteMapping(value = "/orderRecord/{orderId}")
    @ResponseBody
    public boolean cancelAppoint(@PathVariable("orderId") String orderId) {
        boolean success = orderRecordService.cancelAppoint(orderId);
        return success;
    }


    /**
     * 获取预约信息
     * @param orderId
     * @return
     */
    @GetMapping(value = "/orderRecord/info/{orderId}")
    @ResponseBody
    public OrderRecord getOrderRecordInfo(@PathVariable("orderId") String orderId) {
        OrderRecord orderRecord = orderRecordService.getOrderRecordInfo(orderId);
        return orderRecord;
    }


    @PutMapping(value = "/orderRecord/{orderId}")
    @ResponseBody
    public boolean finish(@PathVariable("orderId") String orderId){
        boolean success = orderRecordService.finish(orderId);
        return success;
    }
}