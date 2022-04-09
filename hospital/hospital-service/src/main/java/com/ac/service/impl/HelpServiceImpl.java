package com.ac.service.impl;

import com.ac.bean.HelpCenter;
import com.ac.dao.HelpDao;
import com.ac.service.HelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName HelpServiceImpl
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/25 23:59
 * @Version 1.0
 **/
@Service
public class HelpServiceImpl implements HelpService {

    @Autowired
    private HelpDao helpDao;

    @Override
    public int getAllHelpNum() {
        return helpDao.getAllHelpNum();
    }

    @Override
    public List<HelpCenter> getHelpInfoPage(int pageNum, int pageSize) {
        Map<String, Object> map = new HashMap<>();
        int offset = (pageNum - 1) * pageSize;
        map.put("offset", offset);
        map.put("pageSize", pageSize);
        List<HelpCenter> list = helpDao.getHelpInfoPage(map);
        return list;
    }

    @Override
    public HelpCenter getThisHelpInfo(String id) {
        Map<String,Object> map = new HashMap<>();
        map.put("id", id);
        HelpCenter helpCenter = helpDao.getThisHelpInfo(map);
        return helpCenter;
    }
}
