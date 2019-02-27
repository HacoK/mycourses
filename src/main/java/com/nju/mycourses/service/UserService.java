package com.nju.mycourses.service;

import com.nju.mycourses.DAO.UserRepository;
import com.nju.mycourses.entity.User;
import com.nju.mycourses.enums.UserType;
import com.nju.mycourses.util.CookieUtils;
import com.nju.mycourses.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MailUtil mailUtil;

    public void registerUser(User user){
        userRepository.save(user);
        mailUtil.sendRegisterMail(user.getUserName(),user.getEmail());
    }

    public void activateUser(String userName){
        User user=userRepository.findByUserName(userName);
        user.setActive(true);
        userRepository.save(user);
    }

    public String registerCheck(String userName,String email){
        User user=userRepository.findByUserName(userName);
        if(user!=null){
            if(!user.getEmail().equals(email))
                return "The userName has existed!!!Try another.";
            else{
                if(user.getActive())
                    return "You have registered before...";
                else
                    return "The email should be activated again!!!";
            }
        }else{
            user=userRepository.findByEmail(email);
            if(user!=null){
                if(user.getActive())
                    return "The email has been registered!!!";
                else
                    return "The email should be activated again!!!";
            }else{
                return "Check passed!";
            }
        }
    }

    public String loginCheck(String email,String password){
        User user;
        User userE=userRepository.findByEmail(email);
        User userN=userRepository.findByUserName(email);
        if(userE==null)
            user=userN;
        else
            user=userE;
        if(user==null){
            return "EmailAddress/UserName may be wrong...";
        }else{
            String verify=user.getPassword();
            if(verify.equals(password)){
                if(user.getActive()) {
                    if (user.getType() == UserType.Student)
                        return "Check passed!(ST)";
                    else if (user.getType() == UserType.Teacher)
                        return "Check passed!(TC)";
                    else
                        return "Check passed!(AD)";
                }
                else
                    return "You must activate your email before login!";
            }else{
                return "Sorry!The password may be wrong...";
            }
        }
    }

    public void loginCookie(HttpServletResponse response,String email){
        User user=userRepository.findByEmail(email);
        if(user==null)
            user=userRepository.findByUserName(email);
        CookieUtils.setCookie(response,"userName",user.getUserName());
    }

    public void registerUser(String userName,String email,String password){
        User user=userRepository.findByEmail(email);
        user.setUserName(userName);
        user.setPassword(password);
        userRepository.save(user);
        mailUtil.sendRegisterMail(user.getUserName(),user.getEmail());
    }
}
