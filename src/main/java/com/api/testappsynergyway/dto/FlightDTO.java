package com.api.testappsynergyway.dto;

import com.api.testappsynergyway.entity.Flight;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public class FlightDTO {


    @Value
    @Builder
    public static class FindByCompanyAndStatus {

        String status;
        String company;
        String airplane;
        String departureCountry;
        String destinationCountry;
        String estimatedFlightTime;
        String arrived;
        String departure;
        String delayStartedAt;

        public static FlightDTO.FindByCompanyAndStatus mapToDTO(Flight flight) {
            return FindByCompanyAndStatus
                    .builder()
                    .status(flight.getStatus().getName())
                    .company(flight.getAirplane().getCompany().getName())
                    .airplane(flight.getAirplane().getName())
                    .departureCountry(flight.getDepartureCountry().getName())
                    .destinationCountry(flight.getDestinationCountry().getName())
                    .estimatedFlightTime(flight.getEstimatedFlightTime().toString())
                    .departure(ofNullable(flight.getDeparture()).map(LocalDateTime::toString).orElse(null))
                    .arrived(ofNullable(flight.getArrived()).map(LocalDateTime::toString).orElse(null))
                    .delayStartedAt(ofNullable(flight.getDelayStartedAt()).map(LocalDateTime::toString).orElse(null))
                    .build();
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Create {
        @NotNull
        Long airplaneId;
        @NotNull
        Long destinationCountryId;
        @NotNull
        Long departureCountryId;
        @NotNull
        Integer distance;
        @NotNull
        LocalTime estimatedFlightTime;
        LocalDateTime departure;
        LocalDateTime arrived;
        LocalDateTime delayStartedAt;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class ChangeStatus {
        @NotNull
        Long flightId;
        @NotNull
        Long flightStatusId;
    }

}

