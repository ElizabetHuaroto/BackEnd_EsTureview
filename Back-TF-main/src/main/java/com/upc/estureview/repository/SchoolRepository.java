package com.upc.estureview.repository;

import com.upc.estureview.entitys.School;
import com.upc.estureview.entitys.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SchoolRepository extends JpaRepository<School, Long> {
    @Query("SELECT p FROM School p WHERE p.name like %:prefijo%")
    public List<School> obtenerReportePorNombre(@Param("prefijo") String prefijo);
    public List<School> findByNameStartingWith(String prefix);

    public  List<School>findSchoolByCalification(int calification);
    public List<School>findSchoolByPension(double pension );
    public List<School>findSchoolByDistrict(String district);
}
