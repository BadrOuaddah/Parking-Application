package com.spring.parking.service;

import com.spring.parking.dto.ParkingDto;
import com.spring.parking.entity.Parking;
import com.spring.parking.dao.ParkingDao;
import com.spring.parking.entity.ParkingLot;
import com.spring.parking.mapper.ParkingMapper;
import com.spring.parking.serviceInterface.IParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ParkingService implements IParkingService {
    private ParkingDao parkingDao;
    private ParkingMapper parkingMapper;
    @Autowired
    public ParkingService(ParkingDao parkingDao, ParkingMapper parkingMapper) {
        this.parkingDao = parkingDao;
        this.parkingMapper = parkingMapper;
    }

    public List<ParkingDto> getParking(){
        List<Parking> entities = parkingDao.findAll();
        if (entities == null) {
            return null;
        } else {
            return parkingMapper.toParkingDtos(entities);
        }
    }

    public List<ParkingDto> getParking_mapper_boucle() {
        List<Parking> list = parkingDao.findAll();
        return list.stream().map(parkingMapper::toParkingDto).collect(Collectors.toList());
    }

    public ParkingDto parkingInit(ParkingDto parkingDto) {
        Parking parkingEntity = parkingMapper.toParkingEntity(parkingDto);

        for (ParkingLot parkingLot : parkingEntity.getParkingLots()) {
            parkingLot.setParking(parkingEntity);
        }
        return parkingMapper.toParkingDto(parkingDao.save(parkingEntity));
    }

}