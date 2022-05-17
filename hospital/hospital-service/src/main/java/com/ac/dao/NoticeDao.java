package com.ac.dao;

import com.ac.bean.Notice;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName NoticeDao
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/25 23:39
 * @Version 1.0
 **/
public interface NoticeDao {
    List<Notice> getNoticeInfoPage(Map<String, Object> map);

    Notice getThisNoticeInfo(Map<String, Object> map);

    int getAllNoticeNum();
}
