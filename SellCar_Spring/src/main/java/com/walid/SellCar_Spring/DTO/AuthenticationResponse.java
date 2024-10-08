package com.walid.SellCar_Spring.DTO;


import com.walid.SellCar_Spring.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private String jwt;

    private Long userId;

    private UserRole userRole;




}
