package com.myapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.dto.EmployeeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.time.LocalDateTime;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class EmployeeControllerIT {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        this.objectMapper = new ObjectMapper().findAndRegisterModules();
    }

    @Test
    void shouldCreateEmployee() throws Exception {

        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmployeeId(1);
        dto.setEmployeeName("vikas");
        dto.setSalary(100000D);
        dto.setAge(35);
        dto.setDateOfBirth(LocalDateTime.now());
        dto.setEmail("vikas@gmail.com");
        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())   // 👈 ADD THIS
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.employeeId").value(1))
                .andExpect(jsonPath("$.employeeName").value("vikas"))
                .andExpect(jsonPath("$.salary").value(100000D))
                .andExpect(jsonPath("$.age").value(35))
                .andExpect(jsonPath("$.email").value("vikas@gmail.com"));
    }

    @Test
    void shouldReturnAllEmployees() throws Exception {

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void shouldReturnEmployeeById() throws Exception {

        mockMvc.perform(get("/api/employees/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeId").value(1));
    }

    @Test
    void shouldUpdateEmployeeSalary() throws Exception {

        mockMvc.perform(put("/api/employees/{id}/{salary}", 1, 120000))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salary").value(120000));
    }

    @Test
    void shouldDeleteEmployee() throws Exception {

        mockMvc.perform(delete("/api/employees/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().string("Resource has been deleted successfully"));
    }
}
