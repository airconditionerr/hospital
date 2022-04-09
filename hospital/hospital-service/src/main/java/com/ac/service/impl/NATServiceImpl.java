package com.ac.service.impl;

import com.ac.dao.NATDao;
import com.ac.service.NATService;
import com.ac.utils.TokenUtil;
import com.ac.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName NATServiceImpl
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/26 0:43
 * @Version 1.0
 **/
@Service
public class NATServiceImpl implements NATService {

    @Autowired
    private NATDao natDao;

    @Override
    public boolean natAppoint(Map<String, Object> map, String token) {
        map.put("natId", UUIDUtil.getUUID());
        map.put("userId", TokenUtil.getId(token));
        map.put("natStatus", 1);
        int num = natDao.natAppoint(map);
        if (num != 0){
            return true;
        } else {
            return false;
        }
    }
}
