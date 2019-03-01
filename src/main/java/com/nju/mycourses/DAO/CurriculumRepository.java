package com.nju.mycourses.DAO;

import com.nju.mycourses.entity.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {
    List<Curriculum> findByApprovedEqualsOrderByCurriculumIdAsc(Integer approved);
}
