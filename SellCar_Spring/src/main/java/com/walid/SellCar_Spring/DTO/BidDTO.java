package com.walid.SellCar_Spring.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.walid.SellCar_Spring.entity.Car;
import com.walid.SellCar_Spring.entity.User;
import com.walid.SellCar_Spring.enums.BidStatus;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
@Data
public class BidDTO {


    private Long id;

    private Long price;

    private Long userId;

    private Long carId;

    private BidStatus bidStatus;

    private String username;
    private String carName;
    private String carBrand;
    private String email;
    private String sellerName;




}
