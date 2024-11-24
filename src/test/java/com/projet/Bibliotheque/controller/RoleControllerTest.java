package com.projet.Bibliotheque.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.projet.Bibliotheque.service.RoleService;

@WebMvcTest(RoleController.class)
public class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleService roleService;

    @Test
    public void testCreateNewRole() throws Exception {
        String roleJson = "{ \"roleName\": \"Admin\" }";

        mockMvc.perform(post("/createNewRole")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(roleJson))
                .andExpect(status().isOk());
    }
}
