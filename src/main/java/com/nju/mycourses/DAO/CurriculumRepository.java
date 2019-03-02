package com.nju.mycourses.DAO;

import com.nju.mycourses.entity.Curriculum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {
    List<Curriculum> findByApprovedEqualsOrderByCurriculumIdAsc(Integer approved);
    Page<Curriculum> findByApprovedEquals(Integer approved, Pageable pageable);
    Page<Curriculum> findByCourseIdIn(Collection courseIds, Pageable pageable);
}
