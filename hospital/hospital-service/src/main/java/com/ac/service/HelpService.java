package com.ac.service;

import com.ac.bean.HelpCenter;

import java.util.List;

/**
 * @InterfaceName HelpService
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/25 23:59
 * @Version 1.0
 **/
public interface HelpService {
    List<HelpCenter> getHelpInfoPage(int pageNum, int pageSize);

    HelpCenter getThisHelpInfo(String id);

    int getAllHelpNum();

}
