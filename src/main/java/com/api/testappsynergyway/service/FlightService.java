package com.api.testappsynergyway.service;

import com.api.testappsynergyway.dao.airplane.AirplaneDao;
import com.api.testappsynergyway.dao.country.CountryDao;
import com.api.testappsynergyway.dao.flight.FlightDao;
import com.api.testappsynergyway.dao.flightStatus.FlightStatusDao;
import com.api.testappsynergyway.dto.FlightDTO;
import com.api.testappsynergyway.entity.Flight;
import com.api.testappsynergyway.entity.FlightStatus;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.now;
import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@Service
@FieldDefaults(level = PRIVATE)
public class FlightService {

    final FlightDao flightDao;
    final FlightStatusDao flightStatusDao;
    final AirplaneDao airplaneDao;
    final CountryDao countryDao;

    public FlightService(FlightDao flightDao,
                         FlightStatusDao flightStatusDao,
                         AirplaneDao airplaneDao, CountryDao countryDao) {
        this.flightDao = flightDao;
        this.flightStatusDao = flightStatusDao;
        this.airplaneDao = airplaneDao;
        this.countryDao = countryDao;
    }

    public List<FlightDTO.FindByCompanyAndStatus> findByCompanyAndStatus(Long airCompanyId, Long flightStatusId) {
        return flightDao.findByCompanyIdAndStatusId(airCompanyId, flightStatusId)
                .stream()
                .map(FlightDTO.FindByCompanyAndStatus::mapToDTO)
                .collect(toList());
    }

    public Page<Flight> findActiveFlightsThatDepartedDayAgo(Pageable pageable) {
        return flightDao.findActiveFlightsThatDepartedDayAgo(pageable);
    }

    public Flight create(FlightDTO.Create dto) {
        return flightDao.saveOrUpdate(Flight
                .builder()
                .airplane(airplaneDao.findByIdExpected(dto.getAirplaneId()))
                .status(flightStatusDao.findByIdExpected(4L))
                .distance(dto.getDistance())
                .departureCountry(countryDao.findByIdExpected(dto.getDepartureCountryId()))
                .destinationCountry(countryDao.findByIdExpected(dto.getDestinationCountryId()))
                .estimatedFlightTime(dto.getEstimatedFlightTime())
                .departure(dto.getDeparture())
                .arrived(dto.getArrived())
                .delayStartedAt(dto.getDelayStartedAt())
                .build());
    }

    public Flight changeStatus(FlightDTO.ChangeStatus dto) {
        Flight flight = flightDao.findByIdExpected(dto.getFlightId());
        FlightStatus flightStatus = flightStatusDao.findByIdExpected(dto.getFlightStatusId());
        return flightStatus.equals(flight.getStatus())
                ? flight
                : changeStatusAndDateAccordingStatusId(flight, flightStatus);
    }

    public Page<Map<String, Object>> findCompletedFlightsWithDelays(Pageable pageable){
        return flightDao.findCompletedFlightsWithDelay(pageable);
    }

    private Flight changeStatusAndDateAccordingStatusId(Flight flight, FlightStatus flightStatus) {
        Long statusId = flightStatus.getId();
        if (statusId.equals(1L)) flight.setDeparture(now());
        if (statusId.equals(2L)) flight.setArrived(now());
        if (statusId.equals(3L)) flight.setDelayStartedAt(now());
        flight.setStatus(flightStatus);
        return flightDao.saveOrUpdate(flight);
    }   
}
