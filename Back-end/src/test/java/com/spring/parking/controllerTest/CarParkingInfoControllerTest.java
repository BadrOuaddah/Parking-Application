package com.spring.parking.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.parking.controller.CarParkingInfoController;
import com.spring.parking.entity.CarParkingInfo;
import com.spring.parking.service.CarParkingInfoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CarParkingInfoControllerTest {

    @Mock
    private CarParkingInfoService carParkingInfoService;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private CarParkingInfoController carParkingInfoController;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(carParkingInfoController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void updateCarParkingInfoTest() throws Exception {
        Long vehicleRegistration = 1L;
        CarParkingInfo carParkingInfo = new CarParkingInfo();
        carParkingInfoController.updateCarParkingInfo(vehicleRegistration,carParkingInfo);
        verify(carParkingInfoService, times(1)).updateCar(vehicleRegistration,carParkingInfo);
        mockMvc.perform(put("/api/v1/parking/updateCar/{vehicleRegistration}",vehicleRegistration)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carParkingInfo)))
                .andExpect(status().isOk());
    }

}
