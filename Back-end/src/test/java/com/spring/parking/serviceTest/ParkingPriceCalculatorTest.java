package com.spring.parking.serviceTest;

import com.spring.parking.entity.CarParkingInfo;
import com.spring.parking.entity.ParkingLot;
import com.spring.parking.model.ParkingPriceCalculator;
import com.spring.parking.model.UnparkCarRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ParkingPriceCalculatorTest {

    @InjectMocks
    private ParkingPriceCalculator parkingPriceCalculator;

    @Test
    public void calculatePricePerMinuteTest(){
        ParkingLot parkingLot = new ParkingLot(1L,null,null,2.0);
        CarParkingInfo car = new CarParkingInfo(1L,null,null,null,null, LocalDateTime.of(2024, 1,22,10, 0,0),10.0);
        UnparkCarRequest carRequest = new UnparkCarRequest(LocalDateTime.of(2024, 1,22,11, 0,0));
        double finalPrice = parkingPriceCalculator.calculatePricePerMinute(parkingLot,car,carRequest);
        assertEquals(120.0,finalPrice);
    }
}
