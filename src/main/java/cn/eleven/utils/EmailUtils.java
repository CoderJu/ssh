package cn.eleven.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 邮件发送工具类
 * Created by User on 2017/9/5.
 */
public class EmailUtils {

    /**
     * 发送邮件
     * @param to 邮件接收对象
     * @param code 激活码
     */
    public static  void sendEmail(String to,String code){
        Properties prop = new Properties();
        prop.setProperty("mail.host","smtp.163.com");
        prop.put("mail.smtp.auth", true);
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("18516215719@163.com","6652011l");
            }
        });
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("18516215719@163.com"));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("来自孔明书店的激活邮件");
            message.setContent("<h1>孔明书店的官方激活邮件，点击一下链接完成激活：</h1>" +
                    "<h3><a  href='http://192.168.0.101:8080/ssh/user_active.action?code="+code+"'>点击此处激活</a></h3>","text/html;charset=UTF-8");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] arg){
        sendEmail("1483991357@qq.com","111111111111111111111");
    }
}
