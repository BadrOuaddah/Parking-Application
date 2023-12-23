//package com.spring.parking.configuration;
//
//import com.spring.parking.entity.Parking;
//import com.spring.parking.dao.ParkingDao;
//import com.spring.parking.dao.ParkingLotDao;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//@Configuration
//public class ParkingConfiguration {
//    @Bean
//    CommandLineRunner commandLineRunner(ParkingDao parkingDao, ParkingLotDao parkingLotDao){
//        return args -> {
//            Parking parking = new Parking();
//            if (!parkingDataExists(parkingDao)) {
//                parking.setOpenTime("7:00 AM");
//                parking.setClosetime("12:00 PM");
//
//                parkingDao.save(parking);
//            }
//        };
//    }
//
//
//    private boolean parkingDataExists(ParkingDao parkingDao){
//        return parkingDao.count() > 0;
//    }
//
//    }

