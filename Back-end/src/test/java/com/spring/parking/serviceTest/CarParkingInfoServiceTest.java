package com.spring.parking.serviceTest;

import com.spring.parking.dao.CarParkingInfoDao;

import com.spring.parking.entity.CarParkingInfo;

import com.spring.parking.service.CarParkingInfoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarParkingInfoServiceTest {

    @Mock
    private CarParkingInfoDao carParkingInfoDao;

    @InjectMocks
    private CarParkingInfoService carParkingInfoService;

    @Test
    public void updateCarTestIfVehicleRegistrationNotFound(){
        long vehicleRegistration = 2;
        when(carParkingInfoDao.findById(vehicleRegistration)).thenReturn(null);
        Optional<CarParkingInfo> carParkingInfoOptional = carParkingInfoDao.findById(vehicleRegistration);
        verify(carParkingInfoDao, times(1)).findById(vehicleRegistration);
        assertNull(carParkingInfoOptional);

    }

    @Test
    public void updateCarTest(){
        long vehicleRegistration = 1;
        CarParkingInfo carParkingInfoUpdated = new CarParkingInfo();
        when(carParkingInfoDao.findById(vehicleRegistration)).thenReturn(Optional.of(carParkingInfoUpdated));
        carParkingInfoService.updateCar(vehicleRegistration,carParkingInfoUpdated);
        verify(carParkingInfoDao, times(1)).findById(vehicleRegistration);
        verify(carParkingInfoDao, times(1)).save(carParkingInfoUpdated);
    }
}
