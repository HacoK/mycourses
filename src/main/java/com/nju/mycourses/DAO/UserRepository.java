package com.nju.mycourses.DAO;

import com.nju.mycourses.entity.User;
import com.nju.mycourses.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
    User findByEmail(String email);
    Integer countByType(UserType type);
}
