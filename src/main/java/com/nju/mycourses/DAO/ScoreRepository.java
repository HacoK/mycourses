package com.nju.mycourses.DAO;

import com.nju.mycourses.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findByCurriculumIdOrderByScoreId(Long curriculumId);
    Integer countByCurriculumIdIn(Collection curriculumIds);
}
