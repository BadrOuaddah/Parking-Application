package com.spring.parking.model;

import com.spring.parking.entity.CarParkingInfo;
import com.spring.parking.entity.ParkingLot;

import java.time.LocalDateTime;

public class ParkingPriceCalculator {
    public double calculatePricePerMinute(ParkingLot parkingLot, CarParkingInfo car, UnparkCarRequest unparkCarRequest){
        LocalDateTime start = car.getEntryTime();
        LocalDateTime finish = unparkCarRequest.getFinishTime();
        long durationMinutes = java.time.Duration.between(start, finish).toMinutes();
        return durationMinutes * parkingLot.getPrice();
    }
}
