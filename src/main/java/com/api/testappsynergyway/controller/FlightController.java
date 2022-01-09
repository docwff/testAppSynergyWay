package com.api.testappsynergyway.controller;

import com.api.testappsynergyway.dto.FlightDTO;
import com.api.testappsynergyway.entity.Flight;
import com.api.testappsynergyway.service.FlightService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.CREATED;

@Api(tags = "Endpoints about flights")
@RestController
@FieldDefaults(level = PRIVATE)
@RequestMapping(value = "flight/")
public class FlightController {

    final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @ApiOperation(value = "Find flights by air company and status of flight")
    @GetMapping(value = "findByCompanyAndStatus")
    public List<FlightDTO.FindByCompanyAndStatus> findByCompanyAndStatus(
            @RequestParam(value = "airCompanyId") Long airCompanyId,
            @RequestParam(value = "flightStatusId") Long flightStatusId) {
        return flightService.findByCompanyAndStatus(airCompanyId, flightStatusId);
    }

    @ApiOperation(value = "Find flights that departed day ago and still in active status")
    @GetMapping(value = "findActiveFlightsThatDepartedDayAgo/page/{pageNumber}")
    public Page<Flight> findActiveFlightsThatDepartedDayAgo(@PathVariable(value = "pageNumber") int page) {
        return flightService.findActiveFlightsThatDepartedDayAgo(PageRequest.of(page, 50));
    }

    @ApiOperation(value = "Create new flight")
    @PostMapping(value = "create")
    public ResponseEntity<Flight> create(@RequestBody @Valid FlightDTO.Create dto) {
        return ResponseEntity.status(CREATED)
                .body(flightService.create(dto));
    }

    @ApiOperation(value = "Change status for flight")
    @PatchMapping(value = "changeStatus")
    public Flight changeStatus(@RequestBody @Valid FlightDTO.ChangeStatus dto) {
        return flightService.changeStatus(dto);
    }

    @ApiOperation(value = "Find completed flights with delays")
    @GetMapping(value = "findCompletedFlightsWithDelays/page/{pageNumber}")
    public Page<Map<String, Object>> findCompletedFlightsWithDelays(
            @PathVariable(value = "pageNumber") int page) {
        return flightService.findCompletedFlightsWithDelays(PageRequest.of(page, 3));
    }

}
