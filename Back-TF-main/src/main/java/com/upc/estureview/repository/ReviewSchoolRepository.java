package com.upc.estureview.repository;

import com.upc.estureview.entitys.ReviewSchool;
import com.upc.estureview.entitys.ReviewTeacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewSchoolRepository extends JpaRepository<ReviewSchool, Long> {
    public List<ReviewSchool> findReviewSchoolByIdentidadColegio(Long identidadProfesor);
}
