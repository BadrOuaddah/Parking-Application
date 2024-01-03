package com.spring.parking.controllerTest;

import com.spring.parking.controller.ParkingController;
import com.spring.parking.dto.ParkingDto;
import com.spring.parking.dto.ParkingLotDto;
import com.spring.parking.entity.Parking;
import com.spring.parking.service.ParkingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(controllers = ParkingController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ParkingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParkingService parkingService;

    @Mock
    private com.spring.parking.entity.Parking parking;

    private ParkingDto parkingDto;

    @BeforeEach
    public void init() {
        ParkingLotDto parkingLotDto = new ParkingLotDto();
        parkingDto = ParkingDto.builder()
                .id(1L)
                .openTime("9:00 AM")
                .closeTime("12:00 PM")
                .parkingLots(List.of(parkingLotDto))
                .build();
    }

    @Test
    public void testGetParkingThatReturnParkingList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/parking").accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }
}
