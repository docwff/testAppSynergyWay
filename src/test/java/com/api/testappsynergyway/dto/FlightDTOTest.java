package com.api.testappsynergyway.dto;

import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlightDTOTest {


    private Validator validator;

    @Before
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void flightDTOCreateTest() {
        FlightDTO.Create dto = new FlightDTO.Create(null, null, null, null, null, null, null, null);
        assertEquals(5, validator.validate(dto).size());
        dto.setAirplaneId(4L);
        assertEquals(4, validator.validate(dto).size());
        dto.setDestinationCountryId(45L);
        assertEquals(3, validator.validate(dto).size());
        dto.setDepartureCountryId(12L);
        assertEquals(2, validator.validate(dto).size());
        dto.setDistance(50000);
        assertEquals(1, validator.validate(dto).size());
        dto.setEstimatedFlightTime(LocalTime.of(1, 10));
        assertTrue(validator.validate(dto).isEmpty());


    }

    @Test
    public void changeStatusTest() {
        FlightDTO.ChangeStatus dto = new FlightDTO.ChangeStatus(null, null);
        assertEquals(2, validator.validate(dto).size());
        dto.setFlightId(12L);
        assertEquals(1, validator.validate(dto));
        dto.setFlightStatusId(63L);
    }

}