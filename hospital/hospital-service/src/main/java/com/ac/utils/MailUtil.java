package com.ac.utils;


import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * @ClassName MailUtil
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/15 17:18
 * @Version 1.0
 **/
@Component
public class MailUtil {
    /**
     * 发送邮件
     * @param subject   邮件标题
     * @param content   邮件内容
     * @param receiveAccount    收件人
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public void sendEmail(String subject, String content, String receiveAccount) throws MessagingException, UnsupportedEncodingException {
        String myEmailSMTPHost = "smtp.qq.com";
        //发件人邮箱
        String sendAccount = "291758746@qq.com";
        //邮箱授权码
        String pa = "yllvzwbdxzgcbgbb";
        //收件人邮箱
        //String receiveAccount = "291758746@qq.com";


        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");//使用协议
        props.setProperty("mail.smtp.host", myEmailSMTPHost);//发件人邮箱服务地址
        props.setProperty("mail.smtp.auth", "true");//需要请求认证
        props.setProperty("mail.smtp.port", "465");//ssl端口
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        Session session = Session.getInstance(props, new AuthenticatorUtil());
        session.setDebug(true);
        MimeMessage mime = createMimeMessage(session, sendAccount, receiveAccount, subject, content);
        Transport transport = session.getTransport();//根据session获得邮件传输对象
        transport.connect(sendAccount, pa);
        transport.send(mime, mime.getAllRecipients());
        transport.close();
    }


    /**
     * 创建MIME类型邮件
     * @param session
     * @param sendAccount   发件人
     * @param receiveAccount    收件人
     * @param subject   邮件标题
     * @param content   邮件内容
     * @return
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    private MimeMessage createMimeMessage(Session session,
                                          String sendAccount,
                                          String receiveAccount,
                                          String subject,
                                          String content) throws MessagingException, UnsupportedEncodingException{
        MimeMessage mime = new MimeMessage(session);
        //发件人
        mime.setFrom(sendAccount);
        //收件人
        mime.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveAccount,"hello","UTF-8"));
        //邮件标题
        mime.setSubject(subject,"UTF-8");
        //邮件内容
        mime.setContent(content, "text/html; charset=UTF-8");
        //发送时间
        mime.setSentDate(new Date());
        mime.saveChanges();
        return mime;
    }
}
