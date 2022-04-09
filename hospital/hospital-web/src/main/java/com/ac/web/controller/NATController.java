package com.ac.web.controller;

import com.ac.service.NATService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName NATController
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/26 0:41
 * @Version 1.0
 **/
@Controller
public class NATController {

    @Autowired
    private NATService natService;

    @PostMapping(value = "/nat")
    @ResponseBody
    public boolean natAppoint(@RequestBody Map<String,Object> map, HttpServletRequest request){
        return natService.natAppoint(map, request.getHeader("token"));
    }

}
