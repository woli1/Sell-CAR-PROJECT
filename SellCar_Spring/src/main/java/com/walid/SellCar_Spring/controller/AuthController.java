package com.walid.SellCar_Spring.controller;

import com.walid.SellCar_Spring.entity.User;
import com.walid.SellCar_Spring.DTO.AuthenticationRequest;
import com.walid.SellCar_Spring.DTO.AuthenticationResponse;
import com.walid.SellCar_Spring.DTO.SignupRequest;
import com.walid.SellCar_Spring.DTO.UserDTO;
import com.walid.SellCar_Spring.repositories.UserRepository;
import com.walid.SellCar_Spring.services.auth.AuthService;
import com.walid.SellCar_Spring.services.jwt.UserService;
import com.walid.SellCar_Spring.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin("*")


public class AuthController {
    private final JWTUtil jwtUtil;
    private final AuthService authService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;




    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest){

        if(authService.hasUserWithEmail(signupRequest.getEmail())){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User already exist");

        }
        UserDTO userDTO =authService.signup(signupRequest);

        if(userDTO==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);


    }

    @PostMapping ("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        }
        catch(BadCredentialsException e){
            throw new BadCredentialsException("Invalid username or password");

        }

        final UserDetails userDetails =userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser=userRepository.findFirstByEmail(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        AuthenticationResponse response=new AuthenticationResponse();

        if(optionalUser.isPresent()){
            response.setJwt(jwt);
            response.setUserRole(optionalUser.get().getUserRole());
            response.setUserId(optionalUser.get().getId());

        }
        return response;

    }

}
