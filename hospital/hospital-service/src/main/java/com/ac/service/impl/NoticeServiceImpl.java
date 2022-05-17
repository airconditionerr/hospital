package com.ac.service.impl;

import com.ac.bean.HospitalDepartment;
import com.ac.bean.Notice;
import com.ac.dao.NoticeDao;
import com.ac.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName NoticeServiceImpl
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/25 23:39
 * @Version 1.0
 **/
@Service
@Slf4j
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    @Override
    public List<Notice> getNoticeInfoPage(int pageNum, int pageSize) {
        Map<String, Object> map = new HashMap<>();
        int offset = (pageNum - 1) * pageSize;
        map.put("offset", offset);
        map.put("pageSize", pageSize);
        List<Notice> list = noticeDao.getNoticeInfoPage(map);
        return list;
    }

    @Override
    public Notice getThisNoticeInfo(String id) {
        Map<String,Object> map = new HashMap<>();
        map.put("id", id);
        Notice notice = noticeDao.getThisNoticeInfo(map);
        return notice;
    }

    @Override
    public int getAllNoticeNum() {
        return noticeDao.getAllNoticeNum();
    }
}
