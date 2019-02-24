package com.nju.mycourses;

import com.nju.mycourses.DAO.UserRepository;
import com.nju.mycourses.entity.User;
import com.nju.mycourses.enums.UserType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MycoursesApplicationTests {

    @Test
    public void contextLoads() {
    }

//    @Autowired
//    private MockMvc mvc;
//
//    @Test
//    public void getHello() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().string(equalTo("Greetings from Spring Boot!")));
//    }


    /**
     * 发送模板邮件
     */

//    @Autowired
//    private JavaMailSender javaMailSender;
//    @Autowired
//    private TemplateEngine templateEngine;
//    @Test
//    public void sendTemplateMail() {
//        MimeMessage message = javaMailSender.createMimeMessage();
//        try {
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setFrom("877728156@qq.com");
//            helper.setTo("877728156@qq.com");
//            helper.setSubject("Verify Your Account in My Courses");
//
//            Context context = new Context();
//            context.setVariable("id", "test");
//            String emailContent = templateEngine.process("emailTemplate", context);
//            helper.setText(emailContent, true);
//        } catch (MessagingException e) {
//            throw new RuntimeException("Messaging  Exception !", e);
//        }
//        javaMailSender.send(message);
//    }

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() throws Exception {
        userRepository.save(new User("aa@126.com", "aa", "aa123456",false, UserType.Student));
        userRepository.save(new User("bb@126.com", "bb", "bb123456",false,UserType.Student));
        userRepository.save(new User("cc@126.com", "cc", "cc123456",false,UserType.Teacher));

        Assert.assertEquals(3, userRepository.findAll().size());
        Assert.assertEquals("bb123456", userRepository.findByUserNameOrEmail("bb", "cc2@126.com").getPassword());
        userRepository.delete(userRepository.findByUserName("aa"));
    }
}

