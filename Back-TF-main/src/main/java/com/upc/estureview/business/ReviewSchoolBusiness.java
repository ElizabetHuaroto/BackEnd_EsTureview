package com.upc.estureview.business;

import com.upc.estureview.entitys.ReviewSchool;
import com.upc.estureview.entitys.ReviewTeacher;
import com.upc.estureview.repository.ReviewSchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewSchoolBusiness {
    @Autowired
    private ReviewSchoolRepository reviewSchoolRepository;
    //@Transactional
    public ReviewSchool register(ReviewSchool reviewSchool){
        return reviewSchoolRepository.save(reviewSchool);
    }

    public ReviewSchool actualization(ReviewSchool reviewSchool) throws Exception {
        reviewSchoolRepository.findById(reviewSchool.getId()).orElseThrow(() -> new Exception("No se encontró entidad"));
        return reviewSchoolRepository.save(reviewSchool);
    }

    public ReviewSchool buscar(Long codigo) throws Exception {
        return reviewSchoolRepository.findById(codigo).
                orElseThrow(() -> new Exception("No se encontró entidad"));
    }
    public List<ReviewSchool> CompletList(){
        return reviewSchoolRepository.findAll();
    }
    public List<ReviewSchool>CompletListbyIdSchool(Long codigo) {
        return reviewSchoolRepository.findReviewSchoolByIdentidadColegio(codigo);
    }
    //para control interno
    public ReviewSchool deleteElement(Long code) throws Exception{
        ReviewSchool reviewSchool = reviewSchoolRepository.findById(code).orElseThrow(() -> new Exception("No se encontró entidad"));
        reviewSchoolRepository.delete(reviewSchool);
        return reviewSchool;
    }


}
