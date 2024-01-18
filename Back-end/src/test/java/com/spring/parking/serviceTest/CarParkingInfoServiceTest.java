//package com.spring.parking.serviceTest;
//
//import com.spring.parking.dao.CarParkingInfoDao;
//import com.spring.parking.dto.CarParkingInfoDto;
//import com.spring.parking.entity.CarParkingInfo;
//import com.spring.parking.mapper.CarParkingInfoMapper;
//import com.spring.parking.service.CarParkingInfoService;
//import com.spring.parking.service.ParkingLotService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.mockito.Mockito;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//public class CarParkingInfoServiceTest {
//
//    @Mock
//    private CarParkingInfoDao carParkingInfoDao;
//    @Mock
//    private CarParkingInfoMapper carParkingInfoMapper;
//
//    @InjectMocks
//    private CarParkingInfoService sut;
//
//    private ParkingLotService parkingLotService;
//
//
//    @Test
//    public void testCarService(){
//        // TODO : 'java.lang.AutoCloseable org.mockito.MockitoAnnotations.openMocks(java.lang.Object)'
//        CarParkingInfo carParkingInfo = Mockito.mock(CarParkingInfo.class);
//
//        CarParkingInfoDto carParkingInfoDto = CarParkingInfoDto.builder()
//                .vehicleRegistration(1L)
//                .brand("TestBrand")
//                .color("TestColor")
//                .type("TestType")
//                .totalPrice(10)
//                .build();
//
//        Mockito.when(carParkingInfoMapper.toCarParkingInfoEntity(carParkingInfoDto)).thenReturn(carParkingInfo);
//        Mockito.when(carParkingInfoDao.findByVehicleRegistration(carParkingInfo.getVehicleRegistration())).thenReturn(carParkingInfo);
//        parkingLotService.parkingCar(1L, carParkingInfoDto);
//
//        assertNotNull(carParkingInfo.getVehicleRegistration());
//    }
//}
