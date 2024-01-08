package com.spring.parking.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.spring.parking.controller.CarParkingInfoController;
import com.spring.parking.dao.CarParkingInfoDao;
import com.spring.parking.dto.CarParkingInfoDto;
import com.spring.parking.service.CarParkingInfoService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(controllers = CarParkingInfoController.class)
@ExtendWith(MockitoExtension.class)
public class ControllerTest {
    // TODO : Refactor Controller Test to CarParkingInfoControllerTest

    @MockBean
    CarParkingInfoDao carParkingInfoDao;

    @MockBean
    private CarParkingInfoService carParkingInfoService;

    @Autowired
    private final ObjectMapper objectMapper = new ObjectMapper();
    private MockMvc mockMvc;
    private CarParkingInfoDto carParkingInfoDto;



    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new CarParkingInfoController()).build();
        carParkingInfoDto = CarParkingInfoDto.builder()
                .vehicleRegistration(1)
                .brand("Mercedes")
                .model("AMG")
                .color("Black")
                .type("Class A")
                .entryTime(LocalDateTime.now())
                .totalPrice(10.0)
    .build();

    }

    @Test
    public void shouldCreateNewCar() throws Exception {
        // TODO : Cannot invoke "org.springframework.test.web.servlet.MockMvc.perform(org.springframework.test.web.servlet.RequestBuilder)" because "this.mockMvc" is null

        objectMapper.registerModule(new JavaTimeModule());
        String requestJson = objectMapper.writeValueAsString(carParkingInfoDto);


        mockMvc.perform(post("/api/v1/parking/parkCar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andDo(print());
    }

}
