package com.spring.parking.daoTest;

import com.spring.parking.dao.CarParkingInfoDao;
import com.spring.parking.dao.ParkingDao;
import com.spring.parking.dao.ParkingLotDao;
import com.spring.parking.dto.ParkingDto;
import com.spring.parking.dto.ParkingLotDto;
import com.spring.parking.entity.Parking;
import com.spring.parking.mapper.ParkingMapper;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DaoTest {

    @Autowired
    private ParkingDao parkingDao;
    @Autowired
    private ParkingLotDao parkingLotDao;
    @Autowired
    private CarParkingInfoDao carParkingInfoDao;
    @Autowired
    private ParkingMapper parkingMapper;

    @Test
    public void testSaveParking(){
        List<ParkingLotDto> parkingLotDtoList = new ArrayList<>();
        ParkingLotDto parkingLotDto1 = new ParkingLotDto();
        ParkingLotDto parkingLotDto2 = new ParkingLotDto();
        parkingLotDtoList.add(parkingLotDto1);
        parkingLotDtoList.add(parkingLotDto2);

        ParkingDto parkingDto = ParkingDto.builder()
                .id(1)
                .openTime("8:00 AM")
                .closeTime("11:00 PM")
                .parkingLots(parkingLotDtoList)
                .build();

        //TODO : Cannot invoke toParkingEntity() because parkingMapper is null
        Parking parkingEntity = parkingMapper.toParkingEntity(parkingDto);
        Parking savedParking = parkingDao.save(parkingEntity);
        Assertions.assertThat(savedParking).isNotNull();

    }

    @Test
    public void testFindAllParking(){
        List<ParkingLotDto> parkingLotDtoList = new ArrayList<>();

        ParkingLotDto parkingLotDto1 = new ParkingLotDto();
        ParkingLotDto parkingLotDto2 = new ParkingLotDto();
        parkingLotDtoList.add(parkingLotDto1);
        parkingLotDtoList.add(parkingLotDto2);

        ParkingDto parking_dto_1 = ParkingDto.builder()
                .id(1)
                .openTime("8:00 AM")
                .closeTime("11:00 PM")
                .parkingLots(parkingLotDtoList)
                .build();
        ParkingDto parking_dto_2 = ParkingDto.builder()
                .id(2)
                .openTime("11:00 PM")
                .closeTime("7:00 !M")
                .parkingLots(parkingLotDtoList)
                .build();

        //TODO : Cannot invoke toParkingEntity() because parkingMapper is null
        Parking parking_entity_1 = parkingMapper.toParkingEntity(parking_dto_1);
        Parking parking_entity_2 = parkingMapper.toParkingEntity(parking_dto_2);

        parkingDao.save(parking_entity_1);
        parkingDao.save(parking_entity_2);

        List<Parking> parkingList = parkingDao.findAll();
        Assertions.assertThat(parkingList).isNotNull();
        Assertions.assertThat(parkingList.size()).isEqualTo(2);
    }

}
