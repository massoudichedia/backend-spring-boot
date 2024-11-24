package com.projet.Bibliotheque.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.projet.Bibliotheque.service.JwtService;

@WebMvcTest(JwtController.class)
public class JwtControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @Test
    public void testCreateJwtToken() throws Exception {
        String jwtRequestJson = "{ \"username\": \"test\", \"password\": \"password\" }";

        mockMvc.perform(post("/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jwtRequestJson))
                .andExpect(status().isOk());
    }
}
