package com.spring.parking.service;

import com.spring.parking.dto.CarParkingInfoDto;
import com.spring.parking.dto.ParkingLotDto;
import com.spring.parking.entity.CarParkingInfo;
import com.spring.parking.entity.ParkingLot;
import com.spring.parking.dao.ParkingLotDao;
import com.spring.parking.mapper.CarParkingInfoMapper;
import com.spring.parking.mapper.ParkingLotMapper;
import com.spring.parking.model.ParkingPriceCalculator;
import com.spring.parking.model.UnparkCarRequest;
import com.spring.parking.serviceInterface.ICarParkingInfoService;
import com.spring.parking.serviceInterface.IParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ParkingLotService implements IParkingLotService {

    private final ParkingLotMapper parkingLotMapper;
    private final CarParkingInfoMapper carParkingInfoMapper;
    private ParkingLotDao parkingLotDao;
    private ParkingPriceCalculator parkingPriceCalculator;
    private CarParkingInfoService carParkingInfoService;

    @Autowired
    public ParkingLotService(ParkingLotDao parkingLotDao, CarParkingInfoService carParkingInfoService, ParkingLotMapper parkingLotMapper, CarParkingInfoMapper carParkingInfoMapper, ParkingPriceCalculator parkingPriceCalculator) {
        this.parkingLotDao = parkingLotDao;
        this.carParkingInfoService = carParkingInfoService;
        this.parkingLotMapper = parkingLotMapper;
        this.carParkingInfoMapper = carParkingInfoMapper;
        this.parkingPriceCalculator = parkingPriceCalculator;
    }

    public List<ParkingLotDto> getParkingLots() {
        List<ParkingLot> entities = parkingLotDao.findAll();
        if (entities == null) {
            return null;
        } else {
            return parkingLotMapper.toParkingLotDtos(entities);
        }
    }

    public ParkingLotDto getParkingLot(Long parkingLotNumber){
        return parkingLotMapper.toParkingLotDto(parkingLotDao.findById(parkingLotNumber).get());
    }

    public CarParkingInfoDto unparkingCar(Long parkingLotNumber, UnparkCarRequest unparkCarRequest){
        ParkingLot parkingLot = findParkingLotById(parkingLotNumber);
        CarParkingInfo car = parkingLot.getCarParkingInfo();
        double price = parkingPriceCalculator.calculatePricePerMinute(parkingLot, car, unparkCarRequest);
        car.setTotalPrice(price);
        carParkingInfoService.deleteCar(car);
        parkingLotDao.save(parkingLot);
        return carParkingInfoMapper.toCarParkingInfoDto(car);
    }

    public ParkingLot findParkingLotById(Long parkingLotId){
        return parkingLotDao.findById(parkingLotId).get();
    }

    public void parkingCar(Long parkingLotNumber,CarParkingInfoDto carParkingInfoDto){
        ParkingLot parkingLot = findParkingLotById(parkingLotNumber);
        CarParkingInfo carParkingInfo = carParkingInfoMapper.toCarParkingInfoEntity(carParkingInfoDto);
        parkingLot.setCarParkingInfo(carParkingInfo);
        parkingLotDao.save(parkingLot);
    }

}
