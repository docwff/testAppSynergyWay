package com.api.testappsynergyway.dao.flightStatus;

import com.api.testappsynergyway.dao.util.AbstractDaoImpl;
import com.api.testappsynergyway.dao.util.AbstractRepository;
import com.api.testappsynergyway.entity.FlightStatus;
import org.springframework.stereotype.Service;

@Service
class FlightStatusDaoImpl extends AbstractDaoImpl<FlightStatus, Long> implements FlightStatusDao {

    final FlightStatusRepository flightStatusRepository;

    public FlightStatusDaoImpl(
            AbstractRepository<FlightStatus, Long> abstractRepository,
            FlightStatusRepository flightStatusRepository) {
        super(abstractRepository);
        this.flightStatusRepository = flightStatusRepository;
    }



}
