package com.spring.parking.controller;

import com.spring.parking.dto.CarParkingInfoDto;
import com.spring.parking.dto.ParkingLotDto;
import com.spring.parking.model.UnparkCarRequest;
import com.spring.parking.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parking")
public class ParkingLotsController {


    @Autowired
    private ParkingLotService parkingLotService;

    @GetMapping("/parkingLots")
    public List<ParkingLotDto> getParkingLots(){
        return parkingLotService.getParkingLots();
    }

    @GetMapping("/parkingLot/{parkingLotNumber}")
    public ParkingLotDto getParkingLot(@PathVariable Long parkingLotNumber){
        return parkingLotService.getParkingLot(parkingLotNumber);
    }


    @DeleteMapping("/unparkingCar/{parkingLotNumber}")
    public CarParkingInfoDto unparkingCar(@PathVariable("parkingLotNumber") Long parkingLotNumber, @RequestBody UnparkCarRequest unparkCarRequest){
        return parkingLotService.unparkingCar(parkingLotNumber,unparkCarRequest);
    }

    @PostMapping("/parkCar/{parkingLotNumber}")
    public void parkingCar(@PathVariable("parkingLotNumber") Long parkingLotNumber, @RequestBody CarParkingInfoDto carParkingInfoDto){
        parkingLotService.parkingCar(parkingLotNumber, carParkingInfoDto);
    }

}
