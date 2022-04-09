package com.ac.utils;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @ClassName AuthenticatorUtil
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/15 22:21
 * @Version 1.0
 **/
public class AuthenticatorUtil extends Authenticator {
    public AuthenticatorUtil() {
        super();
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        String username = "291758746@qq.com";
        String pa = "yllvzwbdxzgcbgbb";
        if (username != null && username.length() > 0 && pa != null && pa.length() > 0) {
            return new PasswordAuthentication(username, pa);
        }
        return null;
    }
}
