package com.walid.SellCar_Spring.services.auth;

import com.walid.SellCar_Spring.DTO.SignupRequest;
import com.walid.SellCar_Spring.DTO.UserDTO;
import com.walid.SellCar_Spring.entity.User;
import com.walid.SellCar_Spring.enums.UserRole;
import com.walid.SellCar_Spring.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class AdminServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @PostConstruct
    public void createAnAdminAccount(){
        Optional<User> optionalAdmin =userRepository.findByUserRole(UserRole.ADMIN);

        if (optionalAdmin.isEmpty()){
            User admin =new User();
            admin.setName("Admin");
            admin.setEmail("admin@test.com");
            admin.setUserRole(UserRole.ADMIN);
            admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(admin);
            System.out.println("Admin account created successfully");
        }
        else{
            System.out.println("Admin account already exist!");
        }


    }


    @Override
    public Boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
    @Override
    public UserDTO signup(SignupRequest signupRequest){
        User user =new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setUserRole(UserRole.CUSTOMER);
        return userRepository.save(user).getUserDTO();
    }


}
