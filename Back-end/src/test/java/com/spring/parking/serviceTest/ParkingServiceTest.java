package com.spring.parking.serviceTest;

import com.spring.parking.dao.ParkingDao;
import com.spring.parking.dto.ParkingDto;
import com.spring.parking.entity.Parking;
import com.spring.parking.mapper.ParkingMapper;
import com.spring.parking.service.ParkingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;



//@SpringBootTest
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
        List<Parking> parkingList = Arrays.asList(
                new Parking(1L,null,null),
                new Parking(2L,null,null)
        );
        ParkingDto parkingDtoOne = ParkingDto.builder().build();
        ParkingDto parkingDtoTwo = ParkingDto.builder().build();
        List<ParkingDto> parkingDtoList = Arrays.asList(
                parkingDtoOne,
                parkingDtoTwo
                );
        when(parkingDao.findAll()).thenReturn(parkingList);
        when(parkingMapper.toParkingDtos(parkingList)).thenReturn(parkingDtoList);
        List<ParkingDto> getParkingList = parkingService.getParking();
        assertEquals(getParkingList.size(),parkingList.size());
        assertNotNull(getParkingList);
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
        // TODO : Fix the error : Parking.getParkingLots() is null
        ParkingDto parkingDto = new ParkingDto();
        Parking parkingEntity = new Parking();
        when(parkingMapper.toParkingEntity(parkingDto)).thenReturn(parkingEntity);
        when(parkingDao.save(parkingEntity)).thenReturn(parkingEntity);
        parkingService.parkingInit(parkingDto);
        verify(parkingDao, times(1)).save(parkingEntity);
    }
}
