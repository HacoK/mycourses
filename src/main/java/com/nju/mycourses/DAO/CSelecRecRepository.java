package com.nju.mycourses.DAO;

import com.nju.mycourses.entity.CSelecRec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CSelecRecRepository extends JpaRepository<CSelecRec, Long> {
    List<CSelecRec> findByCurriculumIdAndApprovedOrderByRecordIdAsc(Long curriculumId,Integer approved);
    List<CSelecRec> findByStudentIdAndApprovedNot(Long studentId,Integer approved);
    List<CSelecRec> findByStudentIdAndApprovedEqualsOrderByCurriculumId(Long studentId,Integer approved);
    CSelecRec findByStudentIdAndAndCurriculumId(Long studentId,Long curriculumId);
}
