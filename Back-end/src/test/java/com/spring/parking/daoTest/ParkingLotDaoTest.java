package com.spring.parking.daoTest;

import com.spring.parking.dao.ParkingLotDao;
import com.spring.parking.dto.ParkingLotDto;
import com.spring.parking.entity.ParkingLot;
import com.spring.parking.mapper.ParkingLotMapper;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingLotDaoTest {

    @Autowired
    private ParkingLotDao parkingLotDao;

    @Autowired
    private ParkingLotMapper parkingLotMapper;

    @Test
    public void testToSaveParkingLot(){
        // TODO : not-null property references a null or transient value : com.spring.parking.entity.ParkingLot.parking
        ParkingLotDto parkingLotDto = ParkingLotDto.builder()
                .parkingLotNumber(1)
                .carParkingInfoDto(null)
                .parking(null)
                .price(10.00)
                .build();
        ParkingLot parkingLotEntity = parkingLotMapper.toParkingLotEntity(parkingLotDto);

        ParkingLot parkingLotSaved = parkingLotDao.save(parkingLotEntity);

        Assertions.assertThat(parkingLotSaved).isNotNull();
    }

    @Test
    public void testDeleteParkingLot(){
        // TODO : not-null property references a null or transient value : com.spring.parking.entity.ParkingLot.parking
        ParkingLotDto parkingLotDto = ParkingLotDto.builder()
                .parkingLotNumber(1)
                .price(10.00)
                .build();

        // ! Fix error of toParkingLotEntity method
        ParkingLot parkingLotEntity = parkingLotMapper.toParkingLotEntity(parkingLotDto);

        parkingLotDao.save(parkingLotEntity);

        parkingLotDao.deleteById(parkingLotEntity.getParkingLotNumber());
        Optional<ParkingLot> parkingLotDeleted = parkingLotDao.findById(parkingLotEntity.getParkingLotNumber());

        Assertions.assertThat(parkingLotDeleted).isEmpty();
    }
}
