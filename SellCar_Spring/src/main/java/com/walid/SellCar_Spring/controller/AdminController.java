package com.walid.SellCar_Spring.controller;


import com.walid.SellCar_Spring.DTO.BidDTO;
import com.walid.SellCar_Spring.DTO.CarDTO;
import com.walid.SellCar_Spring.DTO.SearchCarDTO;
import com.walid.SellCar_Spring.services.admin.AdminServ;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")

public class AdminController {

    private final AdminServ adminServ;
    @GetMapping("/cars")
    public ResponseEntity<List<CarDTO>> getAllCars(){
        return ResponseEntity.ok(adminServ.getAllCars());
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable long id){
        return ResponseEntity.ok(adminServ.getCarById(id));
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id){
        adminServ.deleteCar(id);
        return ResponseEntity.ok(null);
    }
    @PostMapping("/car/search")
    public ResponseEntity<List<CarDTO>> searchCar(@RequestBody SearchCarDTO searchCarDTO){
        return ResponseEntity.ok(adminServ.searchCar(searchCarDTO));
    }

    @GetMapping("/car/bids")
    public ResponseEntity<List<BidDTO>> getBids(){
        return ResponseEntity.ok(adminServ.getBids());
    }

    @GetMapping("/car/bid/{bidId}/{status}")
    public ResponseEntity<?> changeBidStatus(@PathVariable Long bidId,@PathVariable String status){
        boolean success=adminServ.changeBidStatus(bidId,status);
        if(success) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();


    }


}
