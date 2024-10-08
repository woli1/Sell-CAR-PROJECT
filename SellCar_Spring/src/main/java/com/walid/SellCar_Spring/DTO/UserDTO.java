package com.walid.SellCar_Spring.DTO;

import com.walid.SellCar_Spring.enums.UserRole;
import lombok.Data;

@Data
public class UserDTO {


    private Long id;
    private String name;
    private String email;
    private UserRole userRole;

}
