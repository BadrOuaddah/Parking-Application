package com.spring.parking.serviceTest;

import com.spring.parking.dao.ParkingDao;
import com.spring.parking.dto.ParkingDto;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;




@ExtendWith(MockitoExtension.class)
public class ParkingServiceTest {
    @Mock
    private ParkingDao parkingDao;

    @Mock
    private ParkingMapper parkingMapper;

    @InjectMocks
    private ParkingService parkingService;


    @Test
    public void getParkingTest(){
        List<Parking> expected = Arrays.asList(
                new Parking(1L,null,null),
                new Parking(2L,null,null)
        );
        ParkingDto parkingDtoOne = ParkingDto.builder().id(1).build();
        ParkingDto parkingDtoTwo = ParkingDto.builder().id(2).build();
        List<ParkingDto> parkingDtoList = Arrays.asList(
                parkingDtoOne,
                parkingDtoTwo
                );
        when(parkingDao.findAll()).thenReturn(expected);
        when(parkingMapper.toParkingDtos(expected)).thenReturn(parkingDtoList);
        List<ParkingDto> result = parkingService.getParking();
        for (int i = 0; i < result.size(); i++) {
            assertEquals(result.get(i).getId(),expected.get(i).getId());
        }
    }

    @Test
    public void getParkingTestIfParkingIsNull(){
        when(parkingDao.findAll()).thenReturn(null);
        List<ParkingDto> getParking = parkingService.getParking();
        assertNull(getParking);
    }

    @Test
    public void getParking_mapper_boucle_test() {
        Parking parking1 = new Parking();
        Parking parking2 = new Parking();
        ParkingDto parkingDto1 = new ParkingDto();
        ParkingDto parkingDto2 = new ParkingDto();
        List<Parking> parkings = Arrays.asList(parking1, parking2);

        when(parkingDao.findAll()).thenReturn(parkings);
        when(parkingMapper.toParkingDto(parking1)).thenReturn(parkingDto1);
        when(parkingMapper.toParkingDto(parking2)).thenReturn(parkingDto2);

        List<ParkingDto> actualResult = parkingService.getParking_mapper_boucle();

        assertNotNull(actualResult);
        assertEquals(2, actualResult.size());
        verify(parkingDao, times(1)).findAll();
        verify(parkingMapper, times(2)).toParkingDto(Mockito.any(Parking.class));
    }

    @Test
    public void parkingInitTest(){
        ParkingDto parkingDto = new ParkingDto();
        Parking parkingEntity = new Parking();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingEntity.setParkingLots(parkingLotList);

        when(parkingMapper.toParkingEntity(parkingDto)).thenReturn(parkingEntity);
        when(parkingDao.save(parkingEntity)).thenReturn(parkingEntity);

        parkingService.parkingInit(parkingDto);
        verify(parkingDao, times(1)).save(parkingEntity);
    }
}
