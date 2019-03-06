package com.nju.mycourses.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Component
public class MailUtil {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateEngine templateEngine;
    public void sendRegisterMail(String userName,String recipient) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("877728156@qq.com");
            helper.setTo(recipient);
            helper.setSubject("Verify Your Account in My Course");

            Context context = new Context();
            context.setVariable("userName", userName);
            String emailContent = templateEngine.process("registerMail", context);
            helper.setText(emailContent, true);
        } catch (MessagingException e) {
            throw new RuntimeException("Messaging Exception!", e);
        }
        javaMailSender.send(message);
    }
    public void sendMailAll(String courseName, String teacherName, String title,String mailContent, List<String> recipients) {
        for(String recipient:recipients) {
            MimeMessage message = javaMailSender.createMimeMessage();
            try {
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setFrom("877728156@qq.com");
                helper.setTo(recipient);
                helper.setSubject(title);

                Context context = new Context();
                context.setVariable("courseName", courseName);
                context.setVariable("teacherName", teacherName);
                context.setVariable("mailContent", mailContent);
                String emailContent = templateEngine.process("mailTemplate", context);
                helper.setText(emailContent, true);
            } catch (MessagingException e) {
                throw new RuntimeException("Messaging Exception!", e);
            }
            javaMailSender.send(message);
        }
    }
}
