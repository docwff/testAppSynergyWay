package com.api.testappsynergyway.dao.flight;

import com.api.testappsynergyway.dao.util.AbstractDao;
import com.api.testappsynergyway.dto.FlightDTO;
import com.api.testappsynergyway.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface FlightDao extends AbstractDao<Flight, Long> {

    List<Flight> findByCompanyIdAndStatusId(Long airCompanyId, Long flightStatusId);
    Page<Flight> findActiveFlightsThatDepartedDayAgo(Pageable pageable);
    Page<Map<String, Object>> findCompletedFlightsWithDelay(Pageable pageable);
}
