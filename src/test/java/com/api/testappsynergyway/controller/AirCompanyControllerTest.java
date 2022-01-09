package com.api.testappsynergyway.controller;

import com.api.testappsynergyway.AbstractIntegrationMockMvcTestConfigure;
import com.api.testappsynergyway.dao.airCompany.AirCompanyDao;
import com.api.testappsynergyway.entity.AirCompany;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Sql(scripts = "classpath:sql/script/airCompanyDataOne.sql", executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:sql/script/deleteData.sql", executionPhase = AFTER_TEST_METHOD)
class AirCompanyControllerTest extends AbstractIntegrationMockMvcTestConfigure {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    AirCompanyDao airCompanyDao;


    @Test
    void findAllTest() throws Exception {
        mockMvc.perform(get("/airCompany/findAll")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(8)))
                .andExpect(jsonPath("$..name", notNullValue()))
                .andExpect(jsonPath("$.[0].length()", is(3)))
                .andExpect(jsonPath("$.[0].keys()", containsInRelativeOrder("name", "airCompanyType", "foundedAt")))
                .andExpect(jsonPath("$.[2].name", is("Wizz Air")))
                .andExpect(jsonPath("$.[0].airCompanyType").value("Major"));
    }

    @Test
    void findByIdRecordShouldBeFound() throws Exception {
        mockMvc.perform(get("/airCompany/id/1").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$.keys()", containsInRelativeOrder("id", "name", "airCompanyType", "foundedAt")))
                .andExpect(jsonPath("$.airCompanyType", notNullValue()))
                .andExpect(jsonPath("$.airCompanyType.name").value("Major"));
    }

    @Test
    void findByIdRecordShouldBeNotFound() throws Exception {
        mockMvc.perform(get("/airCompany/id/1337").contentType(APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("Entity AirCompany with id 1337 not found")));
    }

    @Test
    void createShouldBeCreated() throws Exception {
        String name = "New Airlines Company";
        Long airCompanyTypeId = 1L;
        String content = "{ \"name\": \"" + name + "\", \"airCompanyTypeId\": " + airCompanyTypeId + " }";

        mockMvc.perform(put("/airCompany/createOrGet").contentType(APPLICATION_JSON)
                        .content(content))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$.keys()", containsInRelativeOrder("id", "name", "airCompanyType", "foundedAt")))
                .andExpect(jsonPath("$.airCompanyType", notNullValue()))
                .andExpect(jsonPath("$.name", is("New Airlines Company")))
                .andExpect(jsonPath("$.foundedAt").value(LocalDate.now().toString()));

        assertEquals(9, airCompanyDao.findAll().size());
        AirCompany createdAirCompany = airCompanyDao.findByNameAndAirCompanyTypeId(name, airCompanyTypeId);

        mockMvc.perform(put("/airCompany/createOrGet").contentType(APPLICATION_JSON)
                        .content(content))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createdAirCompany.getId()))
                .andExpect(jsonPath("$.airCompanyType.id").value(airCompanyTypeId))
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    void createShouldBeException() throws Exception {
        mockMvc.perform(put("/airCompany/createOrGet").contentType(APPLICATION_JSON)
                        .content("{\n" + "    \"name\": \"null\",\n" + "    \"airCompanyTypeId\": null\n" + "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(patch("/airCompany/update").contentType(APPLICATION_JSON)
                        .content("{  \"name\": \"WW\",\n" + "    \"airCompanyTypeId\": 3, \"airCompanyId\": 1 }"))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$.keys()", containsInRelativeOrder("id", "name", "airCompanyType", "foundedAt")))
                .andExpect(jsonPath("$.airCompanyType", notNullValue()))
                .andExpect(jsonPath("$.name", is("WW")))
                .andExpect(jsonPath("$.airCompanyType.id", is(3)));
    }

    @Test
    void deleteShouldBeNoContentInResponse() throws Exception {
        mockMvc.perform(delete("/airCompany/delete").param("id", "1")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/airCompany/id/1").contentType(APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error", is(EntityNotFoundException.class.getSimpleName())));
    }
}