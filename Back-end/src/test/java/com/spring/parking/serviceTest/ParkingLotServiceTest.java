package com.spring.parking.serviceTest;

import com.spring.parking.dao.ParkingLotDao;
import com.spring.parking.dto.ParkingLotDto;
import com.spring.parking.entity.ParkingLot;
import com.spring.parking.mapper.CarParkingInfoMapper;
import com.spring.parking.mapper.ParkingLotMapper;
import com.spring.parking.service.CarParkingInfoService;
import com.spring.parking.service.ParkingLotService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

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


}
