package com.upc.estureview.repository;

import com.upc.estureview.entitys.ReviewTeacher;
import com.upc.estureview.entitys.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewTeacherRepository extends JpaRepository<ReviewTeacher, Long> {

    public List<ReviewTeacher> findReviewTeacherByIdentidadProfesor(Long identidadProfesor);

}
