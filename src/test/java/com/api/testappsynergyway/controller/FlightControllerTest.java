package com.api.testappsynergyway.controller;

import com.api.testappsynergyway.AbstractIntegrationMockMvcTestConfigure;
import com.api.testappsynergyway.dao.flight.FlightDao;
import com.api.testappsynergyway.entity.Flight;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import static java.time.Duration.between;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Sql(scripts = {"classpath:sql/script/airCompanyDataOne.sql",
        "classpath:sql/script/airplanesDataOne.sql",
        "classpath:sql/script/flightDataOne.sql"},
        executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:sql/script/deleteData.sql", executionPhase = AFTER_TEST_METHOD)
class FlightControllerTest extends AbstractIntegrationMockMvcTestConfigure {


    @Autowired
    MockMvc mockMvc;
    @Autowired
    FlightDao flightDao;

    @Test
    void findByCompanyAndStatus() throws Exception {
        mockMvc.perform(get("/flight/findByCompanyAndStatus")
                        .param("airCompanyId", "2")
                        .param("flightStatusId", "2")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void findAllInActiveStatusDayBefore() throws Exception {
        mockMvc.perform(get("/flight/findActiveFlightsThatDepartedDayAgo/page/0")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.content.[0].departure").exists())
                .andExpect(jsonPath("$.content.[0].status..id", contains(1)));
    }

    @Test
    void createNewFlightTest() throws Exception {
        mockMvc.perform(post("/flight/create/")
                        .contentType(APPLICATION_JSON)
                        .content(" {\n" +
                                "    \"airplaneId\": 4,\n" +
                                "    \"destinationCountryId\": 52,\n" +
                                "    \"departureCountryId\": 12,\n" +
                                "    \"distance\": 549230,\n" +
                                "    \"estimatedFlightTime\": \"01:14:32\",\n" +
                                "    \"departure\": null,\n" +
                                "    \"arrived\": null,\n" +
                                "    \"delay\": null\n" +
                                "} "))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.status.name").value("PENDING"))
                .andExpect(jsonPath("$.status.id").value(4))
                .andExpect(jsonPath("$.airplane.id").value(4))
                .andExpect(jsonPath("$.departureCountry.id").value(12))
                .andExpect(jsonPath("$.destinationCountry.id").value(52))
                .andExpect(jsonPath("$.distance").value(549230))
                .andExpect(jsonPath("$.estimatedFlightTime").value("01:14:32"))
                .andExpect(jsonPath("$.departure", is(nullValue())))
                .andExpect(jsonPath("$.arrived", is(nullValue())))
                .andExpect(jsonPath("$.delayStartedAt", is(nullValue())))
                .andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    void changeStatusTestSetDelayedStatus() throws Exception {
        Flight flightBefore = flightDao.findByIdExpected(2L);
        assertEquals(4L, flightBefore.getStatus().getId());
        assertNull(flightBefore.getDelayStartedAt());

        mockMvc.perform(patch("/flight/changeStatus/")
                        .contentType(APPLICATION_JSON)
                        .content(" {\n" +
                                "    \"flightId\": 2,\n" +
                                "    \"flightStatusId\": 3\n" +
                                "} "))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.status.id").value(3))
                .andExpect(jsonPath("$.delayStartedAt").exists());

        Flight flightAfter = flightDao.findByIdExpected(2L);
        assertEquals(3L, flightAfter.getStatus().getId());
        assertEquals(LocalDate.now(), flightAfter.getDelayStartedAt().toLocalDate());
    }

    @Test
    void changeStatusTestSetActiveStatus() throws Exception {
        Flight flightBefore = flightDao.findByIdExpected(2L);
        assertEquals(4L, flightBefore.getStatus().getId());
        assertNull(flightBefore.getDeparture());

        mockMvc.perform(patch("/flight/changeStatus/")
                        .contentType(APPLICATION_JSON)
                        .content(" {\n" +
                                "    \"flightId\": 2,\n" +
                                "    \"flightStatusId\": 1\n" +
                                "} "))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.status.id").value(1))
                .andExpect(jsonPath("$.departure").exists());

        Flight flightAfter = flightDao.findByIdExpected(2L);
        assertEquals(1L, flightAfter.getStatus().getId());
        assertEquals(LocalDate.now(), flightAfter.getDeparture().toLocalDate());
    }


    @Test
    void changeStatusTestSetCompletedStatus() throws Exception {
        Flight flightBefore = flightDao.findByIdExpected(2L);
        assertEquals(4L, flightBefore.getStatus().getId());
        assertNull(flightBefore.getArrived());

        mockMvc.perform(patch("/flight/changeStatus/")
                        .contentType(APPLICATION_JSON)
                        .content(" {\n" +
                                "    \"flightId\": 2,\n" +
                                "    \"flightStatusId\": 2\n" +
                                "} "))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.status.id").value(2))
                .andExpect(jsonPath("$.arrived").exists());

        Flight flightAfter = flightDao.findByIdExpected(2L);
        assertEquals(2L, flightAfter.getStatus().getId());
        assertEquals(LocalDate.now(), flightAfter.getArrived().toLocalDate());
    }

    @Test
    @SuppressWarnings("uncheked")
    void findCompletedFlightsWithDelayTest() throws Exception {
        String jsonResponse = mockMvc.perform(get("/flight/findCompletedFlightsWithDelays/page/0")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Map<String, Object> json = new ObjectMapper().readValue(jsonResponse, new TypeReference<>() {});
        List<Map<String, Object>> content = (List<Map<String, Object>>) json.get("content");

       content.forEach(map ->{
           assertNotNull(map.get("company"));
           assertNotNull(map.get("depatrureCountry"));
           assertNotNull(map.get("destinationCountry"));

           LocalTime estimatedFlightTime = LocalTime.parse((CharSequence) map.get("estimatedFlightTime"));
           LocalTime actualFlightTime = LocalTime.parse((CharSequence) map.get("actualFlightTime"));

           assertTrue(actualFlightTime.isAfter(estimatedFlightTime));

           LocalTime delayTime = LocalTime.parse((CharSequence) map.get("delayTime"));
           Duration betweenActualAndEstimated = between(estimatedFlightTime, actualFlightTime);

           assertEquals(delayTime.toSecondOfDay(), betweenActualAndEstimated.getSeconds(),
                   "seconds between delay and (actual - estimated) is not equals");

       });

    }



}