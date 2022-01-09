package com.api.testappsynergyway.service;

import com.api.testappsynergyway.dao.airCompany.AirCompanyDao;
import com.api.testappsynergyway.dao.airplane.AirplaneDao;
import com.api.testappsynergyway.dao.airplaneType.AirplaneTypeDao;
import com.api.testappsynergyway.dao.country.CountryDao;
import com.api.testappsynergyway.dto.AirplaneDTO;
import com.api.testappsynergyway.entity.Airplane;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Optional.ofNullable;
import static lombok.AccessLevel.PRIVATE;

@Service
@FieldDefaults(level = PRIVATE)
public class AirplaneService {

    final AirplaneDao airplaneDao;
    final AirCompanyDao airCompanyDao;
    final CountryDao countryDao;
    final AirplaneTypeDao airplaneTypeDao;

    public AirplaneService(AirplaneDao airplaneDao,
                           AirCompanyDao airCompanyDao, CountryDao countryDao,
                           AirplaneTypeDao airplaneTypeDao) {
        this.airplaneDao = airplaneDao;
        this.airCompanyDao = airCompanyDao;
        this.countryDao = countryDao;
        this.airplaneTypeDao = airplaneTypeDao;
    }

    public Airplane reAssign(AirplaneDTO.ReAssign dto) {
        Airplane airplane = airplaneDao.findByIdExpected(dto.getAirplaneId());
        airplane.setCompany(airCompanyDao.findByIdExpected(dto.getNewAirCompanyId()));
        return airplaneDao.saveOrUpdate(airplane);
    }

    public Airplane create(AirplaneDTO.Create dto) {
        Airplane airplane = new Airplane();
        ofNullable(dto.getAirCompanyId()).map(airCompanyDao::findByIdExpected).ifPresent(airplane::setCompany);
        airplane.setFactory(countryDao.findByIdExpected(dto.getFactoryId()));
        airplane.setType(airplaneTypeDao.findByIdExpected(dto.getAirplaneTypeId()));
        airplane.setName(dto.getName());
        airplane.setFlightDistance(dto.getFlightDistance());
        airplane.setFuelCapacity(dto.getFuelCapacity());
        airplane.setSerialNumber(dto.getSerialNumber());
        airplane.setNumberOfFlights(dto.getNumberOfFlights());
        return airplaneDao.saveOrUpdate(airplane);
    }


}
