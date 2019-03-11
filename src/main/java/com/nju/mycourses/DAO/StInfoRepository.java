package com.nju.mycourses.DAO;

import com.nju.mycourses.entity.StInfo;
import com.nju.mycourses.enums.StType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StInfoRepository extends JpaRepository<StInfo, Long> {
    StInfo findByStudentId(String studentId);
    Integer countByTypeST(StType stType);
}
