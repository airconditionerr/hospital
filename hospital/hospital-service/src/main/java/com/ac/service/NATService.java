package com.ac.service;

import java.util.Map;

/**
 * @ClassName NATService
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/26 0:43
 * @Version 1.0
 **/
public interface NATService {
    boolean natAppoint(Map<String, Object> map, String token);
}
