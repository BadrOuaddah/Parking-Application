package com.spring.parking.configuration;

import com.spring.parking.bean.Parking;
import com.spring.parking.dao.ParkingDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ParkingConfiguration {
//    @Bean
//    CommandLineRunner commandLineRunner(ParkingDao parkingDao){
//        return args -> {
//            if (!parkingDataExists(parkingDao)) {
//                Parking parking = new Parking();
//                parking.setPricePerplacePerminute(2);
//                parking.setOpenTime("7:00");
//                parking.setClosetime("12:00");
//                List<String> newParkingLot = Arrays.asList("Lot 1", "Lot 2", "Lot 3", "Lot 4", "Lot 5");
//                String parkingLotCSV = String.join(",", newParkingLot);
//                parking.setParkingLot(parkingLotCSV);
//                parkingDao.save(parking);
//            }
//        };
//    }
//    private boolean parkingDataExists(ParkingDao parkingDao){
//        return parkingDao.count() > 0;
//    }
}
