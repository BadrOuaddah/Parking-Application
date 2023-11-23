package com.spring.parking.service;

import com.spring.parking.bean.CarParkingInfo;
import com.spring.parking.dao.CarParkingInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarParkingInfoService {

    private CarParkingInfoDao carParkingInfoDao;

    @Autowired
    public CarParkingInfoService(CarParkingInfoDao carParkingInfoDao) {
        this.carParkingInfoDao = carParkingInfoDao;
    }

    public List<CarParkingInfo> getCars() {
        return carParkingInfoDao.findAll();
    }

    public void updateCar(String vehicleRegistration, CarParkingInfo carParkingInfo){
        carParkingInfo.setBrand(carParkingInfo.getBrand());
        carParkingInfo.setModel(carParkingInfo.getModel());
        carParkingInfo.setColor(carParkingInfo.getColor());
        carParkingInfo.setType(carParkingInfo.getType());
        carParkingInfo.setEntryTimestamp(carParkingInfo.getEntryTimestamp());
        carParkingInfo.setFinishTimestamp(carParkingInfo.getFinishTimestamp());
        carParkingInfoDao.save(carParkingInfo);
    }

    public void addCar(CarParkingInfo carParkingInfo){
        carParkingInfoDao.save(carParkingInfo);
    }

    public void deleteCar(String vehicleRegistration){
        carParkingInfoDao.delete(carParkingInfoDao.findByVehicleRegistration(vehicleRegistration));
    }

    //TEST

}
