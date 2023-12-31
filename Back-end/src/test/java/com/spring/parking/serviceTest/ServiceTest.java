package com.spring.parking.serviceTest;

import com.spring.parking.dao.ParkingDao;
import com.spring.parking.dto.ParkingDto;
import com.spring.parking.entity.Parking;
import com.spring.parking.mapper.ParkingMapper;
import com.spring.parking.service.ParkingService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ServiceTest {
    @Mock
    private ParkingDao parkingDao;
    @Mock
    private ParkingMapper parkingMapper;
    @InjectMocks
    private ParkingService sut;


    @Test
    public void getParkingTest(){
        // TODO : 'java.lang.AutoCloseable org.mockito.MockitoAnnotations.openMocks(java.lang.Object)'
        List<Parking> parkings = Arrays.asList(new Parking(), new Parking());
        List<ParkingDto> parkingsDto = Arrays.asList(new ParkingDto(), new ParkingDto());
        Mockito.when(parkingDao.findAll()).thenReturn(parkings);
        Mockito.when(parkingMapper.toParkingDtos(parkings)).thenReturn(parkingsDto);
        List<ParkingDto> actualResult = sut.getParking();
        Assert.assertNull(actualResult);
        Assert.assertEquals(2, actualResult.size());
        Mockito.verify(parkingDao, Mockito.times(1)).findAll();
        Mockito.verify(parkingMapper, Mockito.times(1)).toParkingDtos(Mockito.any(List.class));
    }
}
