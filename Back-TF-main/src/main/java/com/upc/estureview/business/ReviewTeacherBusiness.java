package com.upc.estureview.business;

import com.upc.estureview.entitys.ReviewTeacher;
import com.upc.estureview.entitys.School;
import com.upc.estureview.entitys.Teacher;
import com.upc.estureview.repository.ReviewTeacherRepository;
import com.upc.estureview.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewTeacherBusiness {
    @Autowired
    private ReviewTeacherRepository reviewTeacherRepository;

    //@Transactional
    public ReviewTeacher register(ReviewTeacher reviewTeacher){
        return reviewTeacherRepository.save(reviewTeacher);
    }


    public ReviewTeacher actualization(ReviewTeacher reviewTeacher) throws Exception {
        reviewTeacherRepository.findById(reviewTeacher.getId()).orElseThrow(() -> new Exception("No se encontró entidad"));
        return reviewTeacherRepository.save(reviewTeacher);
    }
    public ReviewTeacher buscar(Long codigo) throws Exception {
        return reviewTeacherRepository.findById(codigo).
                orElseThrow(() -> new Exception("No se encontró entidad"));
    }

    public List<ReviewTeacher> CompletList(){
        return reviewTeacherRepository.findAll();
    }


    public List<ReviewTeacher>CompletListbyIdProfesor(Long codigo) {
        return reviewTeacherRepository.findReviewTeacherByIdentidadProfesor(codigo);
    }
    //para control interno
    public ReviewTeacher deleteElement(Long code) throws Exception{
        ReviewTeacher reviewTeacher = reviewTeacherRepository.findById(code).orElseThrow(() -> new Exception("No se encontró entidad"));
        reviewTeacherRepository.delete(reviewTeacher);
        return reviewTeacher;
    }


}
