package com.spring.parking.daoTest;

import com.spring.parking.dao.CarParkingInfoDao;
import com.spring.parking.dto.CarParkingInfoDto;
import com.spring.parking.entity.CarParkingInfo;
import com.spring.parking.mapper.CarParkingInfoMapper;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarParkingInfoDaoTest {

    @Autowired
    private CarParkingInfoDao carParkingInfoDao;

    @Autowired
    private CarParkingInfoMapper carParkingInfoMapper;

    @Test
    public void testSaveNewCarParkingInfo(){
        CarParkingInfoDto carDto = CarParkingInfoDto.builder().build();
        CarParkingInfo carEntity = carParkingInfoMapper.toCarParkingInfoEntity(carDto);
        CarParkingInfo savedCar = carParkingInfoDao.save(carEntity);
        Assertions.assertThat(savedCar).isNotNull();
    }

    @Test
    public void testFindByVehicleRegistration(){
        CarParkingInfoDto carDto = CarParkingInfoDto.builder()
                .vehicleRegistration(1L)
                .build();
        CarParkingInfo carEntity = carParkingInfoMapper.toCarParkingInfoEntity(carDto);
        carParkingInfoDao.save(carEntity);
        CarParkingInfo vehicleRegistrationFound = carParkingInfoDao.findByVehicleRegistration(1L);
        Assertions.assertThat(vehicleRegistrationFound).isNotNull();
    }


    @Test
    public void testDeleteACar(){
        CarParkingInfoDto carDto = CarParkingInfoDto.builder()
                .vehicleRegistration(1L)
                .build();
        CarParkingInfo carEntity = carParkingInfoMapper.toCarParkingInfoEntity(carDto);
        carParkingInfoDao.save(carEntity);
        carParkingInfoDao.deleteById(1L);
        Optional<CarParkingInfo> carParkingInfoOptional = carParkingInfoDao.findById(carEntity.getVehicleRegistration());
        Assertions.assertThat(carParkingInfoOptional).isEmpty();
    }
}
