package com.nju.mycourses.DAO;

import com.nju.mycourses.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByCurriculumIdOrderByAssignmentId(Long curriculumId);
    List<Assignment> findByCurriculumIdIn(Collection curriculumId);
}
