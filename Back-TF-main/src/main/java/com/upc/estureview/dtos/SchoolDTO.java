package com.upc.estureview.dtos;

import lombok.*;

import javax.persistence.Column;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class SchoolDTO {
    private Long id;
    private String name;
    private double pension;
    private String district;
    private String photo;
    private int calification;

}
