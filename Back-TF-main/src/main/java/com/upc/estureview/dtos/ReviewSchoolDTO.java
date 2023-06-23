package com.upc.estureview.dtos;

import com.upc.estureview.entitys.School;
import com.upc.estureview.entitys.Teacher;
import com.upc.estureview.entitys.User;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class ReviewSchoolDTO {
    private Long id;
    private LocalDate dateReviewS;
    private String reviewS;
    private int calification;
    private double pension;
    private Long identidadColegio;
    private Long identidadUsuario;
}
