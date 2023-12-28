package com.spring.parking.serviceTest;

import com.spring.parking.dao.CarParkingInfoDao;
import com.spring.parking.dao.ParkingDao;
import com.spring.parking.dto.CarParkingInfoDto;
import com.spring.parking.dto.ParkingDto;
import com.spring.parking.entity.CarParkingInfo;
import com.spring.parking.entity.Parking;
import com.spring.parking.mapper.CarParkingInfoMapper;
import com.spring.parking.mapper.ParkingMapper;
import com.spring.parking.service.ParkingLotService;
import com.spring.parking.service.ParkingService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
    @Mock
    private ParkingDao parkingDao;
    @Mock
    private ParkingMapper parkingMapper;
    @InjectMocks
    private ParkingService sut;

    @InjectMocks
    ParkingLotService parkingLotService;


    private CarParkingInfoMapper carParkingInfoMapper;


    @Mock
    private CarParkingInfoDto carParkingInfoDto;

    @Mock
    private CarParkingInfoDao carParkingInfoDao;

    @Before
    public void setUp(){
        sut = new ParkingService(parkingDao, parkingMapper);
    }

    @Test
    public void getParkingTest(){
        List<Parking> parkings = Arrays.asList(new Parking(), new Parking());
        List<ParkingDto> parkingsDto = Arrays.asList(new ParkingDto(), new ParkingDto());

        when(parkingDao.findAll()).thenReturn(parkings);

        when(parkingMapper.toParkingDtos(parkings)).thenReturn(parkingsDto);

        List<ParkingDto> actualResult = sut.getParking();

        Assert.assertNull(actualResult);

        Mockito.verify(parkingDao, Mockito.times(1)).findAll();
        Mockito.verify(parkingMapper, Mockito.times(1)).toParkingDtos(Mockito.any(List.class));

    }

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
