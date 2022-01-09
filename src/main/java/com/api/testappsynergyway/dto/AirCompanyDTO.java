package com.api.testappsynergyway.dto;

import com.api.testappsynergyway.entity.AirCompany;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AirCompanyDTO {

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class CreateUpdate {
        @NotNull(groups = ValidGroup.AIR_COMPANY_UPDATE.class)
        Long airCompanyId;
        @NotBlank(groups = ValidGroup.AIR_COMPANY_CREATE_OR_GET.class)
        String name;
        @NotNull(groups = ValidGroup.AIR_COMPANY_CREATE_OR_GET.class)
        Long airCompanyTypeId;
    }

    @Value
    public static class FindAll {

        String name;
        String airCompanyType;
        LocalDate foundedAt;

        public static FindAll mapToDto(AirCompany ac) {
            return new FindAll(ac.getName(), ac.getAirCompanyType().getName(), ac.getFoundedAt());
        }

    }


}
