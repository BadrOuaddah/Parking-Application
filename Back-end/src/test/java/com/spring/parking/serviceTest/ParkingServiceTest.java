package com.spring.parking.serviceTest;

import com.spring.parking.dao.ParkingDao;
import com.spring.parking.dto.ParkingDto;
import com.spring.parking.dto.ParkingLotDto;
import com.spring.parking.entity.Parking;
import com.spring.parking.entity.ParkingLot;
import com.spring.parking.mapper.ParkingMapper;
import com.spring.parking.service.ParkingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ParkingServiceTest {
    @Mock
    private ParkingDao parkingDao;
    @Mock
    private ParkingMapper parkingMapper;
    @InjectMocks
    private ParkingService sut;


    @Test
    public void getParkingTest(){
        List<Parking> parkings = Arrays.asList(new Parking(), new Parking());
        List<ParkingDto> parkingsDto = Arrays.asList(new ParkingDto(), new ParkingDto());
        Mockito.when(parkingDao.findAll()).thenReturn(parkings);
        Mockito.when(parkingMapper.toParkingDtos(parkings)).thenReturn(parkingsDto);
        List<ParkingDto> actualResult = sut.getParking();
        Assertions.assertThat(actualResult).isNotNull();
        Assertions.assertThat(2).isEqualTo(actualResult.size());
        Mockito.verify(parkingDao, Mockito.times(1)).findAll();
        Mockito.verify(parkingMapper, Mockito.times(1)).toParkingDtos(Mockito.any(List.class));
    }

    @Test
    public void getParking_mapper_boucleTest(){
        List<ParkingDto> result = sut.getParking_mapper_boucle();
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void parkingInitTest(){
        // TODO : java.lang.AssertionError: Expecting actual not to be null
        Parking parking = new Parking();
        Parking parkingSaved = parkingDao.save(parking);

        Assertions.assertThat(parkingSaved).isNotNull();
    }

}
