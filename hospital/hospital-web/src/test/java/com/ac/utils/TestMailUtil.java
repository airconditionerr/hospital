package com.ac.utils;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

/**
 * @ClassName TestMailUtil
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/15 17:24
 * @Version 1.0
 **/
public class TestMailUtil {
    MailUtil mailUtil = new MailUtil();

    @Test
    public void testSendEmail() throws UnsupportedEncodingException, MessagingException {
        mailUtil.sendEmail("Test Mail Util", "Success", "291758746@qq.com");
    }

}
