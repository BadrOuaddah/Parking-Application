package com.spring.parking.serviceInterface;

import com.spring.parking.dto.ParkingDto;

import java.util.List;

public interface IParkingService {
    List<ParkingDto> getParking();

    List<ParkingDto> getParking_mapper_boucle();

    ParkingDto parkingInit(ParkingDto parkingDto);
}
