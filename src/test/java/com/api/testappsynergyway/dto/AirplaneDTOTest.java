package com.api.testappsynergyway.dto;

import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;

import static org.junit.jupiter.api.Assertions.*;

public class AirplaneDTOTest {

    private Validator validator;

    @Before
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void airplaneDTOCreateValid() {
        AirplaneDTO.Create dto = new AirplaneDTO.Create(null, null, null, null, null, null, null, null);
        assertEquals(7, validator.validate(dto).size());
        dto.setName("Airbus 213");
        assertEquals(6, validator.validate(dto).size());
        dto.setFactoryId(32L);
        assertEquals(5, validator.validate(dto).size());
        dto.setFlightDistance(35000);
        assertEquals(4, validator.validate(dto).size());
        dto.setFuelCapacity(70000);
        assertEquals(3, validator.validate(dto).size());
        dto.setNumberOfFlights(562);
        assertEquals(2, validator.validate(dto).size());
        dto.setAirplaneTypeId(14L);
        assertEquals(1, validator.validate(dto).size());
        dto.setSerialNumber("PXQ56244");
        assertTrue(validator.validate(dto).isEmpty());
    }

}