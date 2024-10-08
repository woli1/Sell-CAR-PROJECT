package com.walid.SellCar_Spring.services.auth;
import com.walid.SellCar_Spring.DTO.SignupRequest;
import com.walid.SellCar_Spring.DTO.UserDTO;
public interface AuthService {


    Boolean hasUserWithEmail(String email);


    UserDTO signup (SignupRequest signupRequest);

}
