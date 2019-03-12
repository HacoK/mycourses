package com.nju.mycourses.DAO;

import com.nju.mycourses.entity.Curriculum;
import com.nju.mycourses.enums.StType;
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
    List<Curriculum> findByCourseIdIn(Collection courseIds);
    Integer countByApproved(Integer approved);
    Integer countByApprovedNotAndTypeST(Integer approved, StType typeST);
    Integer countByCourseIdInAndApproved(Collection courseIds,Integer approved);
    List<Curriculum> findByCourseIdInAndApprovedGreaterThan(Collection courseIds,Integer approved);
}
