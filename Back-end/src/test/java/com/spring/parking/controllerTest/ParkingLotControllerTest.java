package com.spring.parking.controllerTest;

import com.spring.parking.controller.ParkingLotsController;
import com.spring.parking.dto.CarParkingInfoDto;
import com.spring.parking.dto.ParkingLotDto;
import com.spring.parking.model.UnparkCarRequest;
import com.spring.parking.service.ParkingLotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ParkingLotControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private ParkingLotService parkingLotService;

    @InjectMocks
    private ParkingLotsController parkingLotsController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(parkingLotsController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getParkingLotsTest() throws Exception {
        List<ParkingLotDto> parkingLotDtoList = Arrays.asList(
          new ParkingLotDto(),
          new ParkingLotDto(),
          new ParkingLotDto()
        );

        when(parkingLotService.getParkingLots()).thenReturn(parkingLotDtoList);
        List<ParkingLotDto> result = parkingLotsController.getParkingLots();
        verify(parkingLotService, times(1)).getParkingLots();
        assertEquals(3,parkingLotDtoList.size());
        mockMvc.perform(get("/api/v1/parking/parkingLots").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void getOneParkingLotTest() throws Exception {
        Long parkingLotNumber = 1L;
        ParkingLotDto parkingDto = new ParkingLotDto();
        when(parkingLotService.getParkingLot(parkingLotNumber)).thenReturn(parkingDto);
        parkingLotsController.getParkingLot(parkingLotNumber);
        verify(parkingLotService, times(1)).getParkingLot(parkingLotNumber);
        assertNotNull(parkingLotsController.getParkingLot(parkingLotNumber));

        mockMvc.perform(get("/api/v1/parking/parkingLot/{parkingLotNumber}",parkingLotNumber).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void unparkingCarTest() throws Exception {
        Long parkingLotNumber = 1L;
        ParkingLotDto parkingLotDto = new ParkingLotDto();
        UnparkCarRequest unparkCarRequest = new UnparkCarRequest(LocalDateTime.now());
        when(parkingLotService.unparkingCar(parkingLotNumber,unparkCarRequest)).thenReturn(null);
        parkingLotsController.unparkingCar(parkingLotNumber,unparkCarRequest);
        verify(parkingLotService,times(1)).unparkingCar(parkingLotNumber,unparkCarRequest);
        mockMvc.perform(delete("/api/v1/parking/unparkingCar/{parkingLotNumber}",parkingLotNumber)
                        .content(objectMapper.writeValueAsString(parkingLotDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void parkingCar() throws Exception {
        Long parkingLotNumber = 1L;
        CarParkingInfoDto carParkingInfoDto = new CarParkingInfoDto();
        parkingLotsController.parkingCar(parkingLotNumber,carParkingInfoDto);
        mockMvc.perform(post("/api/v1/parking/parkCar/{parkingLotNumber}",parkingLotNumber)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(carParkingInfoDto)))
                .andExpect(status().isOk());
    }
}



