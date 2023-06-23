package com.upc.estureview.repository;

import com.upc.estureview.entitys.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query("SELECT p FROM Teacher p WHERE p.name like %:prefijo%")
    public List<Teacher> obtenerReportePorNombre(@Param("prefijo") String prefijo);
    public List<Teacher> findByNameStartingWith(String prefix);
   public Teacher findTeacherById(Long id);

  public   List<Teacher>findTeacherByCalification(int calification);
  public   List<Teacher>findTeacherByPension(double pension );
  public   List<Teacher>findTeacherByCourse(String course);
}
