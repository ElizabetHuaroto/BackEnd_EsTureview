package com.upc.estureview.business;

import com.upc.estureview.entitys.School;
import com.upc.estureview.entitys.Teacher;
import com.upc.estureview.repository.SchoolRepository;
import com.upc.estureview.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SchoolBusiness {
    @Autowired
    private SchoolRepository schoolRepository;

    //@Transactional
    public School register(School school){
        return schoolRepository.save(school);
    }
    //para que se haga la busqueda por nombre
    public List<School> findbyName(String prefijo){
        return schoolRepository.obtenerReportePorNombre(prefijo);
    }
    //lista completa
    public List<School> CompletList(){
        return schoolRepository.findAll();
    }
    //filtros

    public School deleteElement(Long code) throws Exception{
        School school = schoolRepository.findById(code).orElseThrow(() -> new Exception("No se encontró entidad"));
        schoolRepository.delete(school);
        return school;
    }


    public List<School>FindFilteredDistrict(String district){
        return schoolRepository.findSchoolByDistrict(district);
    }
    public List<School>FindFilteredCalification(int calification){
        return schoolRepository.findSchoolByCalification(calification);
    }
    public School buscar(Long codigo) throws Exception {
        return schoolRepository.findById(codigo).
                orElseThrow(() -> new Exception("No se encontró entidad"));
    }
    public List<School>FindFilteredPension(double pension){
        return schoolRepository.findSchoolByPension(pension);
    }
    public School actualization(School school) throws Exception {
        schoolRepository.findById(school.getId()).orElseThrow(() -> new Exception("No se encontró entidad"));
        return schoolRepository.save(school);
    }

}
