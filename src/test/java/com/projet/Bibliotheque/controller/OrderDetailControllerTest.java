package com.projet.Bibliotheque.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.projet.Bibliotheque.service.OrderDetailService;

@WebMvcTest(OrderDetailController.class)
public class OrderDetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderDetailService orderDetailService;

    @Test
    public void testPlaceOrder() throws Exception {
        String orderInputJson = "{ \"products\": [], \"user\": \"testUser\" }";

        mockMvc.perform(post("/placeOrder/true")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderInputJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetOrderDetails() throws Exception {
        mockMvc.perform(get("/getOrderDetails")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllOrderDetails() throws Exception {
        mockMvc.perform(get("/getAllOrderDetails")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
