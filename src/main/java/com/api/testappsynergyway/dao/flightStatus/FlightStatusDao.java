package com.api.testappsynergyway.dao.flightStatus;

import com.api.testappsynergyway.dao.util.AbstractDao;
import com.api.testappsynergyway.entity.FlightStatus;
import org.springframework.cache.annotation.Cacheable;


public interface FlightStatusDao extends AbstractDao<FlightStatus, Long> {
}
