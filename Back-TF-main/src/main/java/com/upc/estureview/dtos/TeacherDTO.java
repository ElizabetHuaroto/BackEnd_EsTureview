package com.upc.estureview.dtos;

import lombok.*;

import javax.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter

public class TeacherDTO {
    private Long id;
    private String name;
    private String course;
    private String photo;
    private double pension;
    private int calification;

}
