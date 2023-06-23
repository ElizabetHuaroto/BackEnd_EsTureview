package com.upc.estureview.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.*;
import javax.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
public class UserDTO {
    private Long id;
    private String nameUser;
    private String typeUser;
    private String email;
    private String password;
    private String districUser;
    private int ageUser;
}
