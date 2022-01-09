package com.api.testappsynergyway.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AirplaneDTO {

    @Value
    public static class ReAssign {
        @NotNull
        Long airplaneId;
        @NotNull
        Long newAirCompanyId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Create {
        @NotBlank
        String name;
        Long airCompanyId;
        @NotNull
        Long factoryId;
        @NotNull
        Integer flightDistance;
        @NotNull
        Integer numberOfFlights;
        @NotNull
        Integer fuelCapacity;
        @NotBlank
        String serialNumber;
        @NotNull
        Long airplaneTypeId;
    }

}
