package com.walid.SellCar_Spring.services.customer;

import com.walid.SellCar_Spring.DTO.*;
import com.walid.SellCar_Spring.DTO.BidDTO;
import java.io.IOException;
import java.util.List;

public interface CustomerService {

    boolean createCar(CarDTO carDTO) throws IOException;
    List<CarDTO> getAllCars();

    CarDTO getCarById(long id);

    void deleteCar(Long id);

    boolean updateCar(Long id,CarDTO carDTO) throws IOException;

    List<CarDTO> searchCar(SearchCarDTO searchCarDTO);

    List<CarDTO> getMyCars(Long userId);

    boolean bidACar(BidDTO bidDTO);


    List<BidDTO>getBidsByUserId(Long userId);

    List<BidDTO> getBidsByCarId(Long carId);

    boolean changeBidStatus(Long bidId,String status);

    AnalyticsDTO getAnalytics(Long userId);

}
