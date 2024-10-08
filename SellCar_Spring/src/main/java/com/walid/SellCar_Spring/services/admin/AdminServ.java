package com.walid.SellCar_Spring.services.admin;

import com.walid.SellCar_Spring.DTO.BidDTO;
import com.walid.SellCar_Spring.DTO.CarDTO;
import com.walid.SellCar_Spring.DTO.SearchCarDTO;

import java.util.List;

public interface AdminServ {
    List<CarDTO> getAllCars();
    CarDTO getCarById(long id);
    void deleteCar(Long id);


    List<CarDTO> searchCar(SearchCarDTO searchCarDTO);
    List<BidDTO>getBids();

    boolean changeBidStatus(Long bidId,String status);

}
