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

//    @Test
//    public void getParking_mapper_boucle_test() {
//        // préparation des jeux de données
//        Parking parking1 = new Parking();
//        Parking parking2 = new Parking();
//        ParkingDto parkingDto1 = new ParkingDto();
//        ParkingDto parkingDto2 = new ParkingDto();
//
//        List<Parking> parkings = Arrays.asList(parking1, parking2);
//
//        Mockito.when(parkingDao.findAll()).thenReturn(parkings); // mock des appels dans la méthode testé
//
//        Mockito.when(parkingMapper.toParkingDto(parking1)).thenReturn(parkingDto1); // mock des appels dans la méthode testé
//        Mockito.when(parkingMapper.toParkingDto(parking2)).thenReturn(parkingDto2); // mock des appels dans la méthode testé
//
//        List<ParkingDto> actualResult = sut.getParking_mapper_boucle(); // appel de la méthode testé
//
//        // assertion sur les données retourné
//        Assert.assertNotNull(actualResult);
//        Assert.assertEquals(2, actualResult.size());
//
//        // assertion sur les appels executé
//        Mockito.verify(parkingDao, Mockito.times(1)).findAll();
//        Mockito.verify(parkingMapper, Mockito.times(2)).toParkingDto(Mockito.any(Parking.class));
//    }

    @Test
    public void parkingInitTest(){
        Parking parking = new Parking(1,"9:00 AM","12:00 PM");
        ParkingDto parkingDto = ParkingDto.builder()
                .id(1)
                .openTime("9:00 AM")
                .closeTime("12:00 PM")
                .build();

        when(parkingMapper.toParkingEntity(parkingDto)).thenReturn(parking);
        when(parkingMapper.toParkingDto(parking)).thenReturn(parkingDto);
        parkingDao.save(parking);
//        when(parkingDao.save(parking)).thenReturn(parkingDto);
        verify(parkingDao, times(1)).save(parking);
        verify(parkingMapper, times(1)).toParkingEntity(parkingDto);
        verify(parkingMapper, times(1)).toParkingDto(parking);


    }

//        public ParkingDto parkingInit(ParkingDto parkingDto) {
//        Parking parkingEntity = parkingMapper.toParkingEntity(parkingDto);
//
//        for (ParkingLot parkingLot : parkingEntity.getParkingLots()) {
//            parkingLot.setParking(parkingEntity);
//        }
//        return parkingMapper.toParkingDto(parkingDao.save(parkingEntity));
//    }

}
