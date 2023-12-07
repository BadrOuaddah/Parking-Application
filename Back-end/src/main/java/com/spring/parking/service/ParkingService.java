package com.spring.parking.service;

import com.spring.parking.dto.ParkingDto;
import com.spring.parking.entity.Parking;
import com.spring.parking.dao.ParkingDao;
import com.spring.parking.entity.ParkingLot;
import com.spring.parking.mapper.ParkingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ParkingService {
    private ParkingDao parkingDao;
    private ParkingMapper parkingMapper;
    @Autowired
    public ParkingService(ParkingDao parkingDao, ParkingMapper parkingMapper) {
        this.parkingDao = parkingDao;
        this.parkingMapper = parkingMapper;
    }

    public List<Parking> getParking(){
        return parkingDao.findAll();
    }

    public ParkingDto parkingInit(ParkingDto parkingDto) {
        Parking parkingEntity = parkingMapper.toParkingEntity(parkingDto);

        for (ParkingLot parkingLot : parkingEntity.getParkingLots()) {
            parkingLot.setParking(parkingEntity);
        }
        return parkingMapper.toParkingDto(parkingDao.save(parkingEntity));
    }

}