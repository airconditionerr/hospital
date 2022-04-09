package com.ac.exception;

/**
 * @ClassName LoginException
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/1 23:05
 * @Version 1.0
 **/
public class LoginException extends Exception{
    public LoginException(String errorMsg) {
        super(errorMsg);
    }
}
