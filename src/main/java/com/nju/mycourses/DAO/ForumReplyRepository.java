package com.nju.mycourses.DAO;

import com.nju.mycourses.entity.ForumReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumReplyRepository extends JpaRepository<ForumReply, Long> {
    Long countByTopicId(Long topicId);
    ForumReply findFirstByTopicIdOrderByReplyNumDesc(Long topicId);
}
