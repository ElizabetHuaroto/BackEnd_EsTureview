package com.upc.estureview.business;

import com.upc.estureview.entitys.Teacher;
import com.upc.estureview.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherBusiness {
    @Autowired
    private TeacherRepository teacherRepository;
    //@Transactional
    public Teacher register(Teacher teacher){
        return teacherRepository.save(teacher);
    }
    //para que se haga la busqueda por nombre
    public List<Teacher> findbyName(String prefijo){
        return teacherRepository.obtenerReportePorNombre(prefijo);
    }
    //lista completa
    public List<Teacher> CompletList(){
        return teacherRepository.findAll();
    }
    //filtros

    public List<Teacher>FindFilteredCourse(String course){
        return teacherRepository.findTeacherByCourse(course);
    }
    public List<Teacher>FindFilteredCalification(int calification){
        return teacherRepository.findTeacherByCalification(calification);
    }
    public Teacher buscar(Long codigo) throws Exception {
        return teacherRepository.findById(codigo).
                orElseThrow(() -> new Exception("No se encontró entidad"));
    }
    public List<Teacher>FindFilteredPension(double pension){
        return teacherRepository.findTeacherByPension(pension);
    }
    public Teacher actualization(Teacher teacher) throws Exception {
        teacherRepository.findById(teacher.getId()).orElseThrow(() -> new Exception("No se encontró entidad"));
        return teacherRepository.save(teacher);
    }
    public Teacher deleteElement(Long code) throws Exception{
        Teacher teacher = teacherRepository.findById(code).orElseThrow(() -> new Exception("No se encontró entidad"));
        teacherRepository.delete(teacher);
        return teacher;
    }


}
