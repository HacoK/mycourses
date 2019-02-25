package com.nju.mycourses.service;

import com.nju.mycourses.DAO.UserRepository;
import com.nju.mycourses.entity.User;
import com.nju.mycourses.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            return "The userName has existed!!!Try another.";
        }else{
            user=userRepository.findByEmail(email);
            if(user!=null){
                return "The email has been registered!!!";
            }else{
                return "Check passed!";
            }
        }
    }

    public String loginCheck(String email,String password){
        User user=userRepository.findByEmail(email);
        if(user==null){
            return "Sorry!The email address may be wrong...";
        }else{
            String verify=user.getPassword();
            if(verify.equals(password)){
                return "Check passed!";
            }else{
                return "Sorry!The password may be wrong...";
            }
        }
    }
}
