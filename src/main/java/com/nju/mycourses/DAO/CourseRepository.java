package com.nju.mycourses.DAO;

import com.nju.mycourses.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByTeacherIdOrderByApprovedDesc(Long teacherId);
    List<Course> findByApprovedEqualsOrderByCourseIdAsc(Integer approved);
}
