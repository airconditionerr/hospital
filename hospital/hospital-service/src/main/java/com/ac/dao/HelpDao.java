package com.ac.dao;

import com.ac.bean.HelpCenter;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName HelpDao
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/26 0:00
 * @Version 1.0
 **/
public interface HelpDao {
    List<HelpCenter> getHelpInfoPage(Map<String, Object> map);

    HelpCenter getThisHelpInfo(Map<String, Object> map);

    int getAllHelpNum();

}
