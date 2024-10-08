package com.walid.SellCar_Spring.repositories;

import com.walid.SellCar_Spring.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CarRepository extends JpaRepository<Car,Long> {


     List<Car> findAllByUserId(Long userId);

     long countByUserId(Long userId);

     long countByUserIdAndSoldTrue(Long userId);
}
