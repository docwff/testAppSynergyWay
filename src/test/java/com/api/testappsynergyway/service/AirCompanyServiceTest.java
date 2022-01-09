package com.api.testappsynergyway.service;

import com.api.testappsynergyway.dao.airCompany.AirCompanyDao;
import com.api.testappsynergyway.dao.airCompanyType.AirCompanyTypeDao;
import com.api.testappsynergyway.dao.airplane.AirplaneDao;
import com.api.testappsynergyway.dao.airplaneType.AirplaneTypeDao;
import com.api.testappsynergyway.dao.country.CountryDao;
import com.api.testappsynergyway.dto.AirCompanyDTO;
import com.api.testappsynergyway.entity.AirCompany;
import com.api.testappsynergyway.entity.AirCompanyType;
import com.api.testappsynergyway.entity.AirplaneType;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityExistsException;
import java.time.LocalDate;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = PRIVATE)
class AirCompanyServiceTest {

    @InjectMocks
    AirCompanyService airCompanyService;
    @Mock
    AirCompanyDao airCompanyDao;
    @Mock
    AirCompanyTypeDao airCompanyTypeDao;

    final String companyKLMName = "KLM";
    final AirCompany airCompany = new AirCompany(4L, companyKLMName, new AirCompanyType("TYPE COMPANY 1"), LocalDate.now());

    @BeforeEach
    void setUp() {
        lenient().when(airCompanyDao.findByIdExpected(4L)).thenReturn(airCompany);
        lenient().when(airCompanyDao.saveOrUpdate(any(AirCompany.class))).thenAnswer(i -> i.getArgument(0));
    }

    @Test
    void updateAirCompanyShouldNotSaveBecauseValuesIsNotPresent() {
        AirCompanyDTO.CreateUpdate dto = new AirCompanyDTO.CreateUpdate(4L, null, null);

        AirCompany airCompany = airCompanyService.update(dto);
        assertEquals(LocalDate.now(), airCompany.getFoundedAt());
        assertEquals(companyKLMName, airCompany.getName());
        assertEquals("TYPE COMPANY 1", airCompany.getAirCompanyType().getName());
        assertEquals(4L, airCompany.getId());
        verify(airCompanyDao, never()).saveOrUpdate(any(AirCompany.class));
        verify(airCompanyTypeDao, never()).findByIdExpected(anyLong());
    }

    @Test
    void updateAirCompanyShouldSaveBecauseNameIsPresent() {
        String newCompanyName = "Ryanair";
        AirCompanyDTO.CreateUpdate dto = new AirCompanyDTO.CreateUpdate(4L, newCompanyName, null);

        AirCompany airCompany = airCompanyService.update(dto);
        assertEquals(LocalDate.now(), airCompany.getFoundedAt());
        assertEquals(newCompanyName, airCompany.getName());
        assertEquals("TYPE COMPANY 1", airCompany.getAirCompanyType().getName());
        assertEquals(4L, airCompany.getId());
        verify(airCompanyDao, times(1)).saveOrUpdate(any(AirCompany.class));
        verify(airCompanyTypeDao, never()).findByIdExpected(anyLong());
    }

    @Test
    void updateAirCompanyShouldSaveBecauseAirCompanyTypeIsPresent() {
        AirCompanyType airCompanyType = new AirCompanyType(1L, "Major");
        lenient().when(airCompanyTypeDao.findByIdExpected(1L)).thenReturn(airCompanyType);
        AirCompanyDTO.CreateUpdate dto = new AirCompanyDTO.CreateUpdate(4L, null, 1L);

        AirCompany airCompany = airCompanyService.update(dto);
        assertEquals(LocalDate.now(), airCompany.getFoundedAt());
        assertEquals(companyKLMName, airCompany.getName());
        assertEquals(airCompanyType.getName(), airCompany.getAirCompanyType().getName());
        assertEquals(4L, airCompany.getId());
        verify(airCompanyDao, times(1)).saveOrUpdate(any(AirCompany.class));
        verify(airCompanyTypeDao, times(1)).findByIdExpected(anyLong());
    }

    @Test
    void updateShouldBeExceptionByAlreadyExcistRecordInDB() {
        String ryanair = "Ryanair";
        AirCompanyType airCompanyType = new AirCompanyType(1L, "Major");
        AirCompany airCompany = new AirCompany(ryanair,airCompanyType,LocalDate.now());
        when(airCompanyTypeDao.findByIdExpected(1L)).thenReturn(airCompanyType);
        when(airCompanyDao.findByNameAndAirCompanyTypeId(ryanair, 1L)).thenReturn(airCompany);
        AirCompanyDTO.CreateUpdate dto = new AirCompanyDTO.CreateUpdate(4L, "Ryanair", 1L);

        try {
            airCompanyService.update(dto);
        } catch (EntityExistsException e) {
            assertEquals("AirCompany with name: Ryanair and type: Major already exists.", e.getMessage());
        }
        verify(airCompanyDao, never()).saveOrUpdate(any(AirCompany.class));
        verify(airCompanyTypeDao, times(1)).findByIdExpected(anyLong());
    }
}