package com.api.testappsynergyway.dao.flightStatus;

import com.api.testappsynergyway.dao.util.AbstractRepository;
import com.api.testappsynergyway.entity.FlightStatus;
import org.springframework.stereotype.Repository;

@Repository
interface FlightStatusRepository extends AbstractRepository<FlightStatus, Long>  {
}
