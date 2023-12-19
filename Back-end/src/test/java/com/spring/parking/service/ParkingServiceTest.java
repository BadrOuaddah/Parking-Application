package com.spring.parking.service;

import com.spring.parking.dao.ParkingDao;
import com.spring.parking.dto.ParkingDto;
import com.spring.parking.entity.Parking;
import com.spring.parking.mapper.ParkingMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class ParkingServiceTest {

    @Mock
    private ParkingDao parkingDao; // mock des beans injecté dans le system under test

    @Mock
    private ParkingMapper parkingMapper; // mock des beans injecté dans le system under test

    @InjectMocks
    private ParkingService sut; // system under test
    @Test
    public void getParking_test() {
        // préparation des jeux de données
        List<Parking> parkings = Arrays.asList(new Parking(), new Parking());
        List<ParkingDto> parkingsDto = Arrays.asList(new ParkingDto(), new ParkingDto());

        Mockito.when(parkingDao.findAll()).thenReturn(parkings); // mock des appels dans la méthode testé

        Mockito.when(parkingMapper.toParkingDto(parkings)).thenReturn(parkingsDto); // mock des appels dans la méthode testé

        List<ParkingDto> actualResult = sut.getParking(); // appel de la méthode testé

        // assertion sur les données retourné
        Assert.assertNotNull(actualResult);
        Assert.assertEquals(2, actualResult.size());

        // assertion sur les appels executé
        Mockito.verify(parkingDao, Mockito.times(1)).findAll(); // on vérifié que les services sont appelé le bon nombre de fois
        Mockito.verify(parkingMapper, Mockito.times(1)).toParkingDto(Mockito.any(List.class)); // par exemple dans le cas ou j'ai pas ajoué le mapper avec list on vas bouclé et le
    }
    @Test
    public void getParking_null_test() {
        // préparation des jeux de données
        List<Parking> parkings = Arrays.asList(new Parking(), new Parking());
        List<ParkingDto> parkingsDto = Arrays.asList(new ParkingDto(), new ParkingDto());

        Mockito.when(parkingDao.findAll()).thenReturn(null); // mock des appels dans la méthode testé

        List<ParkingDto> actualResult = sut.getParking(); // appel de la méthode testé

        // assertion sur les données retourné
        Assert.assertNull(actualResult);

        // assertion sur les appels executé
        Mockito.verify(parkingDao, Mockito.times(1)).findAll(); // on vérifié que les services sont appelé le bon nombre de fois
        Mockito.verify(parkingMapper, Mockito.times(0)).toParkingDto(Mockito.any(List.class)); // par exemple dans le cas ou j'ai pas ajoué le mapper avec list on vas bouclé et le
    }

    @Test
    public void getParking_mapper_boucle_test() {
        // préparation des jeux de données
        Parking parking1 = new Parking();
        Parking parking2 = new Parking();
        ParkingDto parkingDto1 = new ParkingDto();
        ParkingDto parkingDto2 = new ParkingDto();

        List<Parking> parkings = Arrays.asList(parking1, parking2);

        Mockito.when(parkingDao.findAll()).thenReturn(parkings); // mock des appels dans la méthode testé

        Mockito.when(parkingMapper.toParkingDto(parking1)).thenReturn(parkingDto1); // mock des appels dans la méthode testé
        Mockito.when(parkingMapper.toParkingDto(parking2)).thenReturn(parkingDto2); // mock des appels dans la méthode testé

        List<ParkingDto> actualResult = sut.getParking_mapper_boucle(); // appel de la méthode testé

        // assertion sur les données retourné
        Assert.assertNotNull(actualResult);
        Assert.assertEquals(2, actualResult.size());

        // assertion sur les appels executé
        Mockito.verify(parkingDao, Mockito.times(1)).findAll();
        Mockito.verify(parkingMapper, Mockito.times(2)).toParkingDto(Mockito.any(Parking.class));
    }
}
