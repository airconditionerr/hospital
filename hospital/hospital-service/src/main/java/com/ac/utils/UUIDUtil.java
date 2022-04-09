package com.ac.utils;

import java.util.UUID;

/**
 * @ClassName UUIDUtil
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/25 20:32
 * @Version 1.0
 **/
public class UUIDUtil {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
