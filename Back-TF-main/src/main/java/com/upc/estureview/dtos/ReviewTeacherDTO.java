package com.upc.estureview.dtos;

import com.upc.estureview.entitys.Teacher;
import com.upc.estureview.entitys.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class ReviewTeacherDTO {

    private Long id;
    private LocalDate dateReviewT;
    private String reviewT;
    private int calification;
    private double payForHour;
    private Long identidadProfesor;
    private Long identidadUsuario;

}
