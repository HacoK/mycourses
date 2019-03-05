package com.nju.mycourses.DAO;

import com.nju.mycourses.entity.ForumTopic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ForumTopicRepository extends JpaRepository<ForumTopic, Long> {
    Page<ForumTopic> findByCourseId(Long courseId, Pageable pageable);
}
