package com.ac.service;

import com.ac.bean.Notice;

import java.util.List;

/**
 * @InterfaceName NoticeService
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/25 23:39
 * @Version 1.0
 **/
public interface NoticeService {
    List<Notice> getNoticeInfoPage(int pageNum, int pageSize);

    Notice getThisNoticeInfo(String id);

    int getAllNoticeNum();
}
