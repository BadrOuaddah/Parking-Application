package com.spring.parking.controllerTest;

import com.spring.parking.controller.ParkingLotsController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ParkingLotsController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ParkingLotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetParkingLots() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/parking//parkingLots").accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

    }

}
