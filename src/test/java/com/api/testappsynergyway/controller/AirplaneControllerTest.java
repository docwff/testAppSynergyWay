package com.api.testappsynergyway.controller;

import com.api.testappsynergyway.AbstractIntegrationMockMvcTestConfigure;
import com.api.testappsynergyway.dao.airplane.AirplaneDao;
import com.api.testappsynergyway.entity.Airplane;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Sql(scripts = {"classpath:sql/script/airCompanyDataOne.sql",
        "classpath:sql/script/airplanesDataOne.sql"},
        executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:sql/script/deleteData.sql", executionPhase = AFTER_TEST_METHOD)
class AirplaneControllerTest extends AbstractIntegrationMockMvcTestConfigure {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    AirplaneDao airplaneDao;

    @Test
    @Order(1)
    void reAssign() throws Exception {
        Airplane beforeAirplane = airplaneDao.findByIdExpected(1L);
        assertEquals(8L, beforeAirplane.getCompany().getId());

        mockMvc.perform(patch("/airplane/reAssign")
                        .contentType(APPLICATION_JSON)
                        .content("{ \"airplaneId\": 1, \"newAirCompanyId\": 5 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name", is(beforeAirplane.getName())))
                .andExpect(jsonPath("$.serialNumber", is(beforeAirplane.getSerialNumber())))
                .andExpect(jsonPath("$.numberOfFlights", is(beforeAirplane.getNumberOfFlights())))
                .andExpect(jsonPath("$.flightDistance", is(beforeAirplane.getFlightDistance())))
                .andExpect(jsonPath("$.fuelCapacity", is(beforeAirplane.getFuelCapacity())))
                .andExpect(jsonPath("$.type.id").value(beforeAirplane.getType().getId()))
                .andExpect(jsonPath("$.factory.id").value(beforeAirplane.getFactory().getId()))
                .andExpect(jsonPath("$.company.id").value(5));

        assertEquals(5L, airplaneDao.findByIdExpected(1L).getCompany().getId());
    }

    @Test
    @Order(2)
    void createShouldBeCreated() throws Exception {
        mockMvc.perform(post("/airplane/create")
                        .contentType(APPLICATION_JSON)
                        .content(" {\n" +
                                "    \"name\": \"Airbus 343\",\n" +
                                "    \"airCompanyId\": null,\n" +
                                "    \"factoryId\": 32,\n" +
                                "    \"flightDistance\": 6500,\n" +
                                "    \"numberOfFlights\": 23,\n" +
                                "    \"fuelCapacity\": 50000,\n" +
                                "    \"serialNumber\": \"FXO25623\",\n" +
                                "    \"airplaneTypeId\": 6\n" +
                                "} "))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.company", is(nullValue())))
                .andExpect(jsonPath("$.name").value("Airbus 343"))
                .andExpect(jsonPath("$.factory.id").value(32))
                .andExpect(jsonPath("$.serialNumber").value("FXO25623"))
                .andExpect(jsonPath("$.numberOfFlights").value(23))
                .andExpect(jsonPath("$.flightDistance").value(6500))
                .andExpect(jsonPath("$.fuelCapacity").value(50000))
                .andExpect(jsonPath("$.type.id").value(6))
                .andExpect(jsonPath("$.createdAt".split("T")[0], is(LocalDate.now().toString())));
        assertEquals(10L, airplaneDao.findAll().size());
    }


}