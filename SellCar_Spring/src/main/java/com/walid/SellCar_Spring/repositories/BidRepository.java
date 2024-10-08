package com.walid.SellCar_Spring.repositories;

import com.walid.SellCar_Spring.DTO.BidDTO;
import com.walid.SellCar_Spring.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository

public interface BidRepository extends JpaRepository<Bid, Long> {

    List<Bid> findAllByUserId(Long userId);

    List<Bid> findAlByCarId(Long carId);
}
