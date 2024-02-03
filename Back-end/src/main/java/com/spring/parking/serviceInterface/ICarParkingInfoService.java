package com.spring.parking.serviceInterface;

import com.spring.parking.entity.CarParkingInfo;

public interface ICarParkingInfoService {

    void updateCar(long vehicleRegistration, CarParkingInfo carParkingInfo);

    void deleteCar(CarParkingInfo car);

    CarParkingInfo save(CarParkingInfo carParkingInfo);
}
