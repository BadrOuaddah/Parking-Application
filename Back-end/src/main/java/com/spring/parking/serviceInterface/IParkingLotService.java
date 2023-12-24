package com.spring.parking.serviceInterface;

import com.spring.parking.dto.CarParkingInfoDto;
import com.spring.parking.dto.ParkingLotDto;
import com.spring.parking.entity.ParkingLot;
import com.spring.parking.model.UnparkCarRequest;

import java.util.List;

public interface IParkingLotService {
    List<ParkingLotDto> getParkingLots();

    ParkingLotDto getParkingLot(Long parkingLotNumber);

    CarParkingInfoDto unparkingCar(Long parkingLotNumber, UnparkCarRequest unparkCarRequest);

    ParkingLot findParkingLotById(Long parkingLotId);

    void parkingCar(Long parkingLotNumber,CarParkingInfoDto carParkingInfoDto);

}
