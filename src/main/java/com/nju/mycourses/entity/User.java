package com.nju.mycourses.entity;

import com.nju.mycourses.enums.UserType;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Boolean active;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType type;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) { this.password = password; }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public User() {
    }

    public User(String userName, String email, String password, UserType type) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.active = false;
        this.type = type;
    }

    public String getStudentID(){
        return email.substring(0,email.indexOf('@'));
    }

    public String getStudentGrade(){
        return email.substring(0,2);
    }
}
