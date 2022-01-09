package com.api.testappsynergyway.service;

import com.api.testappsynergyway.dao.airCompany.AirCompanyDao;
import com.api.testappsynergyway.dao.airplane.AirplaneDao;
import com.api.testappsynergyway.dao.airplaneType.AirplaneTypeDao;
import com.api.testappsynergyway.dao.country.CountryDao;
import com.api.testappsynergyway.dto.AirplaneDTO;
import com.api.testappsynergyway.entity.*;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = PRIVATE)
class AirplaneServiceTest {

    @InjectMocks
    AirplaneService airplaneService;
    @Mock
    AirCompanyDao airCompanyDao;
    @Mock
    CountryDao countryDao;
    @Mock
    AirplaneTypeDao airplaneTypeDao;
    @Mock
    AirplaneDao airplaneDao;

    final AirCompany airCompany = new AirCompany(4L, "Ryanair", new AirCompanyType("TYPE COMPANY 1"), LocalDate.now());
    final AirplaneType airplaneType = new AirplaneType(3L, "TYPE1");
    final Country country = new Country(5L, "US", "USA", "Inited States Of America");

    AirplaneDTO.Create CREATE_DTO;

    @BeforeEach
    void setUp() {
        lenient().when(airCompanyDao.findByIdExpected(4L)).thenReturn(airCompany);
        lenient().when(airplaneTypeDao.findByIdExpected(3L)).thenReturn(airplaneType);
        lenient().when(countryDao.findByIdExpected(5L)).thenReturn(country);
        lenient().when(airplaneDao.saveOrUpdate(any(Airplane.class))).thenReturn(any(Airplane.class));
        CREATE_DTO = new AirplaneDTO.Create("Airbus 641", 4L, 5L, 3455, 4, 45000, "FGS4591", 3L);
    }

    @Test
    void createShouldBeCreatedWithCompanyId() {
        airplaneService.create(CREATE_DTO);

        verify(airplaneDao, times(1)).saveOrUpdate(any(Airplane.class));
        verify(airplaneTypeDao, times(1)).findByIdExpected(anyLong());
        verify(countryDao, times(1)).findByIdExpected(anyLong());
        verify(airCompanyDao, times(1)).findByIdExpected(anyLong());
    }

    @Test
    void createShouldBeCreatedWithOutCompanyId() {
        CREATE_DTO.setAirCompanyId(null);
        airplaneService.create(CREATE_DTO);

        verify(airplaneDao, times(1)).saveOrUpdate(any(Airplane.class));
        verify(airplaneTypeDao, times(1)).findByIdExpected(anyLong());
        verify(countryDao, times(1)).findByIdExpected(anyLong());
        verify(airCompanyDao, never()).findByIdExpected(anyLong());
    }

}