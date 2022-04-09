package com.ac.service.impl;

import com.ac.dao.FeedbackDao;
import com.ac.service.FeedbackService;
import com.ac.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName FeedbackServiceImpl
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/25 23:05
 * @Version 1.0
 **/
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackDao feedbackDao;

    @Override
    public boolean feedback(Map<String, String> map) {
        map.put("feedbackId", UUIDUtil.getUUID());
        int num = feedbackDao.feedback(map);
        if (num != 0){
            return true;
        } else {
            return false;
        }
    }
}
