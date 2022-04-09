package com.ac.web.controller;

import com.ac.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @ClassName FeedBackController
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/25 23:04
 * @Version 1.0
 **/
@Controller
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping(value = "/feedback")
    @ResponseBody
    public boolean feedback(@RequestBody Map<String,String> map){
        return feedbackService.feedback(map);
    }

}
