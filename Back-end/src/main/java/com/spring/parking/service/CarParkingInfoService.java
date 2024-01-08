package com.spring.parking.service;

import com.spring.parking.entity.CarParkingInfo;
import com.spring.parking.dao.CarParkingInfoDao;
import com.spring.parking.serviceInterface.ICarParkingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CarParkingInfoService implements ICarParkingInfoService {

    private CarParkingInfoDao carParkingInfoDao;

    @Autowired
    public CarParkingInfoService(CarParkingInfoDao carParkingInfoDao) {
        this.carParkingInfoDao = carParkingInfoDao;
    }

    public void updateCar(long vehicleRegistration, CarParkingInfo carParkingInfo){
        Optional<CarParkingInfo> carParkingInfoOptional = carParkingInfoDao.findById(vehicleRegistration);
        if(carParkingInfoOptional.isPresent()){
            CarParkingInfo carParkingInfoGet = carParkingInfoOptional.get();
            carParkingInfoGet.setBrand(carParkingInfo.getBrand());
            carParkingInfoGet.setModel(carParkingInfo.getModel());
            carParkingInfoGet.setColor(carParkingInfo.getColor());
            carParkingInfoGet.setType(carParkingInfo.getType());
            carParkingInfoGet.setEntryTime(carParkingInfo.getEntryTime());
            carParkingInfoDao.save(carParkingInfoGet);
        }
        else {
            System.out.println("vehicleRegistration of this car not found !");
        }

    }

    public void deleteCar(CarParkingInfo car){
        carParkingInfoDao.delete(car);
    }

    public void save(CarParkingInfo carParkingInfo){
        carParkingInfoDao.save(carParkingInfo);
    }
}
