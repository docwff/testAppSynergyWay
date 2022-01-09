package com.api.testappsynergyway.dto;

import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;

import static org.junit.jupiter.api.Assertions.*;

public class AirCompanyDTOTest {

    private Validator validator;

    @Before
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void validAirCompanyDtoCreate() {
        Class<ValidGroup.AIR_COMPANY_CREATE_OR_GET> createGroup = ValidGroup.AIR_COMPANY_CREATE_OR_GET.class;
        assertTrue(validator.validate(new AirCompanyDTO.CreateUpdate(null, "EVA Air", 31L), createGroup).isEmpty());
        assertEquals(2, validator.validate(new AirCompanyDTO.CreateUpdate(null, null, null), createGroup).size());
        assertEquals(1, validator.validate(new AirCompanyDTO.CreateUpdate(null, null, 32L), createGroup).size());
        assertEquals(1, validator.validate(new AirCompanyDTO.CreateUpdate(null, "Pacific", null), createGroup).size());
    }

    @Test
    public void validAirCompanyDtoUpdate() {
        Class<ValidGroup.AIR_COMPANY_UPDATE> updateGroup = ValidGroup.AIR_COMPANY_UPDATE.class;
        assertTrue(validator.validate(new AirCompanyDTO.CreateUpdate(1L, null, null), updateGroup).isEmpty());
        assertEquals(1, validator.validate(new AirCompanyDTO.CreateUpdate(null, "KLM", 32L), updateGroup).size());
        assertEquals(1, validator.validate(new AirCompanyDTO.CreateUpdate(null, null, 11L), updateGroup).size());
        assertEquals(1, validator.validate(new AirCompanyDTO.CreateUpdate(null, "Pacific", null), updateGroup).size());
    }

}