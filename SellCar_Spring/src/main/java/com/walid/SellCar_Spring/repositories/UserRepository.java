package com.walid.SellCar_Spring.repositories;


import com.walid.SellCar_Spring.entity.User;
import com.walid.SellCar_Spring.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findFirstByEmail(String email);
    Optional <User> findByUserRole(UserRole userRole);
}
