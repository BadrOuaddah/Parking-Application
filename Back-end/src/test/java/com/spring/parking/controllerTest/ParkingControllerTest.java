package com.spring.parking.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.parking.controller.ParkingController;
import com.spring.parking.dto.ParkingDto;
import com.spring.parking.dto.ParkingLotDto;
import com.spring.parking.service.ParkingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = ParkingController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ParkingControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ParkingService parkingService;

    @Mock
    private ParkingDto parkingDto;
    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private ParkingController parkingController;

    @BeforeEach
    public void init() {
        ParkingLotDto parkingLotDto = new ParkingLotDto();
        parkingDto = ParkingDto.builder()
                .id(1L)
                .openTime("9:00 AM")
                .closeTime("12:00 PM")
                .parkingLots(Arrays.asList(parkingLotDto))
                .build();
    }

    @Test
    public void testGetParkingThatReturnParkingList() throws Exception {
        List<ParkingDto> parkingDtoList = Arrays.asList(
                new ParkingDto(),
                new ParkingDto()
        );
        when(parkingService.getParking()).thenReturn(parkingDtoList);
        List<ParkingDto> result = parkingController.getParking();
        verify(parkingService, times(1)).getParking();
        assertEquals(result.size(),parkingDtoList.size());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/parking").accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void createParkingInitThatReturnCreated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/parking/init")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(parkingDto)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
