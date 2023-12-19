package com.spring.parking.controller;

import com.spring.parking.dto.ParkingDto;
import com.spring.parking.entity.Parking;
import com.spring.parking.service.IParkingService;
import com.spring.parking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parking")
public class ParkingController {


    @Autowired
    private IParkingService parkingService;

    @GetMapping
    public List<ParkingDto> getParking() { // les services doivent retourné des DTO pas des entity
        return parkingService.getParking();
    }

    @PostMapping("/init")
    public ParkingDto parkingInit(@RequestBody ParkingDto parkingDto){
        return parkingService.parkingInit(parkingDto);
    }
}
