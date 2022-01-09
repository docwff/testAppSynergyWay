package com.api.testappsynergyway.dao.flight;

import com.api.testappsynergyway.dao.util.AbstractDaoImpl;
import com.api.testappsynergyway.dao.util.AbstractRepository;
import com.api.testappsynergyway.dto.FlightDTO;
import com.api.testappsynergyway.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
class FlightDaoImpl extends AbstractDaoImpl<Flight, Long> implements FlightDao {

    final FlightRepository flightRepository;

    public FlightDaoImpl(
            AbstractRepository<Flight, Long> abstractRepository,
            FlightRepository flightRepository) {
        super(abstractRepository);
        this.flightRepository = flightRepository;
    }

    @Override
    public List<Flight> findByCompanyIdAndStatusId(Long airCompanyId, Long flightStatusId) {
        return flightRepository.findByCompanyIdAndStatusId(airCompanyId, flightStatusId);
    }

    @Override
    public Page
            <Flight> findActiveFlightsThatDepartedDayAgo(Pageable pageable) {
        return flightRepository.findActiveFlightsThatDepartedDayAgo(pageable);
    }

    @Override
    public Page<Map<String, Object>> findCompletedFlightsWithDelay(Pageable pageable) {

        Page<Map<String, Object>> completedFlightsWithDelay = flightRepository.findCompletedFlightsWithDelay(pageable);
        System.out.println("pageable = " + completedFlightsWithDelay);
        return completedFlightsWithDelay;
    }
}
