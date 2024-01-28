package com.spring.parking.serviceTest;

import com.spring.parking.dao.ParkingLotDao;
import com.spring.parking.dto.CarParkingInfoDto;
import com.spring.parking.dto.ParkingLotDto;
import com.spring.parking.entity.CarParkingInfo;
import com.spring.parking.entity.ParkingLot;
import com.spring.parking.mapper.CarParkingInfoMapper;
import com.spring.parking.mapper.ParkingLotMapper;
import com.spring.parking.model.UnparkCarRequest;
import com.spring.parking.service.CarParkingInfoService;
import com.spring.parking.service.ParkingLotService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ParkingLotServiceTest {

    @Mock
    private ParkingLotDao parkingLotDao;
    @Mock
    private ParkingLotMapper parkingLotMapper;
    @Mock
    private CarParkingInfoService carParkingInfoService;
    @Mock
    private CarParkingInfoMapper carParkingInfoMapper;

    @InjectMocks
    private ParkingLotService parkingLotService;

    @Test
    public void getParkingLotsTest(){
        ParkingLot parkingLotOne = new ParkingLot();
        ParkingLot parkingLotTwo = new ParkingLot();
        List<ParkingLot> parkingLots = Arrays.asList(parkingLotOne, parkingLotTwo);
        when(parkingLotDao.findAll()).thenReturn(parkingLots);
        parkingLotService.getParkingLots();
        verify(parkingLotDao, times(1)).findAll();
        verify(parkingLotMapper, times(1)).toParkingLotDtos(parkingLots);
        assertNotNull(parkingLotService.getParkingLots());
    }

    @Test
    public void getParkingLotsTestIfParkingLotNull(){
        when(parkingLotDao.findAll()).thenReturn(null);
        parkingLotService.getParkingLots();
        verify(parkingLotDao, times(1)).findAll();
        assertNull(parkingLotService.getParkingLots());
    }

    @Test
    public void getOneParkingLot(){
        Long parkingLotNumber = 1L;
        ParkingLotDto parkingLotDto = new ParkingLotDto();
        ParkingLot parkingLot = new ParkingLot();
        when(parkingLotMapper.toParkingLotDto(parkingLot)).thenReturn(parkingLotDto);
        when(parkingLotDao.findById(1L)).thenReturn(Optional.of(parkingLot));
        parkingLotService.getParkingLot(parkingLotNumber);
        verify(parkingLotDao, times(1)).findById(parkingLotNumber);
        verify(parkingLotMapper, times(1)).toParkingLotDto(parkingLot);
        assertNotNull(parkingLotService.getParkingLot(parkingLotNumber));
    }

    @Test
    public void parkingCarTest(){
        Long parkingLotNumber = 1L;
        ParkingLot parkingLot = new ParkingLot();
        CarParkingInfoDto carParkingInfoDto = new CarParkingInfoDto();
        CarParkingInfo carParkingInfo = new CarParkingInfo();
        when(parkingLotDao.findById(1L)).thenReturn(Optional.of(parkingLot));
        when(carParkingInfoMapper.toCarParkingInfoEntity(carParkingInfoDto)).thenReturn(carParkingInfo);
        parkingLotService.parkingCar(parkingLotNumber,carParkingInfoDto);
        verify(parkingLotDao, times(1)).findById(parkingLotNumber);
        verify(parkingLotDao, times(1)).save(parkingLot);
    }


    @Test
    public void calculatePricePerMinuteTest(){
        ParkingLot parkingLot = new ParkingLot(1L,null,null,2.0);
        CarParkingInfo car = new CarParkingInfo(1L,null,null,null,null,LocalDateTime.of(2024, 1,22,10, 0,0),10.0);
        UnparkCarRequest carRequest = new UnparkCarRequest(LocalDateTime.of(2024, 1,22,11, 0,0));
        double finalPrice = parkingLotService.calculatePricePerMinute(parkingLot,car,carRequest);
        assertEquals(120.0,finalPrice);
    }

    @Test
    public void unparkingCarTest(){
        // TODO : Test unparkingCar
    }

}