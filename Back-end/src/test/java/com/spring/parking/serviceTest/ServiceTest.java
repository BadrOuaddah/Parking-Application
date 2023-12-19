package com.spring.parking.serviceTest;

import com.spring.parking.dao.CarParkingInfoDao;
import com.spring.parking.dto.CarParkingInfoDto;
import com.spring.parking.entity.CarParkingInfo;
import com.spring.parking.mapper.CarParkingInfoMapper;
import com.spring.parking.service.ParkingLotService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {


    @InjectMocks
    ParkingLotService parkingLotService;

    @InjectMocks
    CarParkingInfoMapper carParkingInfoMapper;


    @Mock
    private CarParkingInfo carParkingInfo;

    @Mock
    private CarParkingInfoDao carParkingInfoDao;


    @Test
    public void testCarService(){
        CarParkingInfo carParkingInfo = Mockito.mock(CarParkingInfo.class);

        CarParkingInfoDto carParkingInfoDto = CarParkingInfoDto.builder()
                .vehicleRegistration(1L)
                .brand("TestBrand")
                .color("TestColor")
                .type("TestType")
                .totalPrice(10)
                .build();

        when(carParkingInfoMapper.toCarParkingInfoEntity(carParkingInfoDto)).thenReturn(carParkingInfo);
        when(carParkingInfoDao.getReferenceById(carParkingInfo.getVehicleRegistration())).thenReturn(carParkingInfo);
        parkingLotService.parkingCar(1L, carParkingInfoDto);

        assertNotNull(carParkingInfo.getVehicleRegistration());
    }



}
