package com.nju.mycourses;

import com.nju.mycourses.DAO.AssignmentRepository;
import com.nju.mycourses.DAO.CurriculumRepository;
import com.nju.mycourses.DAO.ForumReplyRepository;
import com.nju.mycourses.DAO.UserRepository;
import com.nju.mycourses.entity.Assignment;
import com.nju.mycourses.entity.Curriculum;
import com.nju.mycourses.entity.User;
import com.nju.mycourses.enums.UserType;
import com.nju.mycourses.util.ExcelUtil;
import com.nju.mycourses.util.FileUtil;
import com.nju.mycourses.util.ZipCompress;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MycoursesApplicationTests {

//    @Test
//    public void contextLoads() {
//    }


//    /**
//     * 发送模板邮件
//     */

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
//            helper.setTo("1269897230@qq.com");
//            helper.setSubject("Verify Your Account in My Courses");
//
//            Context context = new Context();
//            context.setVariable("userName", "Haco");
//            String emailContent = templateEngine.process("registerMail", context);
//            helper.setText(emailContent, true);
//        } catch (MessagingException e) {
//            throw new RuntimeException("Messaging  Exception !", e);
//        }
//        javaMailSender.send(message);
//    }


//    @Test
//    public void test() throws Exception {
//        Thread.sleep(3000);
//        System.setProperty("webdriver.chrome.driver", "D:/Python3.7.0/Scripts/chromedriver.exe");
//        WebDriver webDriver = new ChromeDriver();
//        webDriver.manage().window().maximize();
//        webDriver.manage().deleteAllCookies();
//        // 与浏览器同步非常重要，必须等待浏览器加载完毕
//        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//
//        Actions action = new Actions(webDriver);//-------------------------------------------声明一个动作
//
//
//        //打开目标地址
//        webDriver.get("http://localhost:8080/login");
//        webDriver.findElement(By.id("email")).sendKeys("hacok");
//        Thread.sleep(2000);
//        webDriver.findElement(By.id("password")).sendKeys("hacok");
//        Thread.sleep(2000);
//        webDriver.findElement(By.xpath("//html/body/section/div/div/div/div[2]/div/form/div[4]/button")).click();
//        Thread.sleep(2000);
//        webDriver.findElement(By.xpath("//*[@id=\"4\"]/button")).click();
//        Thread.sleep(2000);
//        webDriver.findElement(By.xpath("/html/body/div/div[2]/div/ul/li[5]/a")).click();
//        Thread.sleep(2000);
//        webDriver.findElement(By.xpath("//*[@id=\"9\"]")).click();
//        Thread.sleep(2000);
//        webDriver.findElement(By.xpath("/html/body/div/div[2]/div/ul/li[2]/a")).click();
//        Thread.sleep(2000);
//        webDriver.findElement(By.xpath("/html/body/div/div[2]/div/ul/li[2]/dl/dd[2]/a")).click();
//
//        //暂停5秒钟后关闭
//        Thread.sleep(5000);
//        webDriver.quit();
//    }


    @Test
    public void test() throws Exception {
        InputStream is = new FileInputStream("C:\\Users\\Kevin\\Desktop\\test.xlsx");;
        Map<String,Double> map=ExcelUtil.getInstance().readScoreExcel(is,"test.xlsx");
        System.out.println(map);
    }
}

