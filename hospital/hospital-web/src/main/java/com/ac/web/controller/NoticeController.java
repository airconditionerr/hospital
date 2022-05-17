package com.ac.web.controller;

import com.ac.bean.HospitalDepartment;
import com.ac.bean.Notice;
import com.ac.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName NoticeController
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/25 23:39
 * @Version 1.0
 **/
@Controller
@Slf4j
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping(value = "/notice/{pageNum}/{pageSize}")
    @ResponseBody
    public List<Notice> getNoticeInfoPage(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        return noticeService.getNoticeInfoPage(pageNum, pageSize);
    }


    @GetMapping(value = "/notice/{id}")
    @ResponseBody
    public Notice getThisNoticeInfo(@PathVariable("id")String id) {
        log.info("----------------进入获取当前公告信息操作----------------");
        Notice notice = noticeService.getThisNoticeInfo(id);
        log.info("----------------获取当前公告信息成功----------------");
        return notice;
    }


    @GetMapping(value = "/notice/num")
    @ResponseBody
    public int getAllNoticeNum(){
        return noticeService.getAllNoticeNum();
    }

}
