package com.ac.web.controller;

import com.ac.bean.HelpCenter;
import com.ac.bean.Notice;
import com.ac.service.HelpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName HelpController
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/25 23:59
 * @Version 1.0
 **/
@Controller
@Slf4j
public class HelpController {

    @Autowired
    private HelpService helpService;

    @GetMapping(value = "/helpCenter/page/{pageNum}/{pageSize}")
    @ResponseBody
    public List<HelpCenter> getHelpInfoPage(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        return helpService.getHelpInfoPage(pageNum, pageSize);
    }


    @GetMapping(value = "/helpCenter/info/{id}")
    @ResponseBody
    public HelpCenter getThisHelpInfo(@PathVariable("id")String id) {
        log.info("----------------进入获取当前公告信息操作----------------");
        HelpCenter helpCenter = helpService.getThisHelpInfo(id);
        log.info("----------------获取当前公告信息成功----------------");
        return helpCenter;
    }

    @GetMapping(value = "/helpCenter/num")
    @ResponseBody
    public int getAllHelpNum(){
        return helpService.getAllHelpNum();
    }
}
