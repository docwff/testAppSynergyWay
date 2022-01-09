package com.api.testappsynergyway.service;

import com.api.testappsynergyway.dao.airCompany.AirCompanyDao;
import com.api.testappsynergyway.dao.airCompanyType.AirCompanyTypeDao;
import com.api.testappsynergyway.dto.AirCompanyDTO;
import com.api.testappsynergyway.entity.AirCompany;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@Service
@FieldDefaults(level = PRIVATE)
public class AirCompanyService {

    final AirCompanyDao airCompanyDao;
    final AirCompanyTypeDao airCompanyTypeDao;

    public AirCompanyService(AirCompanyDao airCompanyDao, AirCompanyTypeDao airCompanyTypeDao) {
        this.airCompanyDao = airCompanyDao;
        this.airCompanyTypeDao = airCompanyTypeDao;
    }

    private Optional<AirCompany> getByNameAndAirCompanyTypeId(String name, Long airCompanyTypeId) {
        return ofNullable(airCompanyDao.findByNameAndAirCompanyTypeId(name, airCompanyTypeId));
    }

    private AirCompany saveOrUpdate(AirCompany airCompany) {
        return airCompanyDao.saveOrUpdate(airCompany);
    }

    public AirCompany saveOrUpdate(String name, Long airCompanyType) {
        return saveOrUpdate(AirCompany.builder()
                .name(name)
                .airCompanyType(airCompanyTypeDao.findByIdExpected(airCompanyType))
                .build());
    }

    public AirCompany findById(Long id) {
        return airCompanyDao.findByIdExpected(id);
    }

    public void delete(Long id) {
        airCompanyDao.deleteById(id);
    }

    public List<AirCompanyDTO.FindAll> findAll() {
        return airCompanyDao.findAll()
                .stream()
                .map(AirCompanyDTO.FindAll::mapToDto)
                .collect(toList());
    }

    public AirCompany createOrGet(AirCompanyDTO.CreateUpdate dto) {
        return getByNameAndAirCompanyTypeId(dto.getName(), dto.getAirCompanyTypeId())
                .orElseGet(() -> saveOrUpdate(dto.getName(), dto.getAirCompanyTypeId()));
    }

    public AirCompany update(AirCompanyDTO.CreateUpdate dto) {
        AirCompany airCompany = findById(dto.getAirCompanyId());
        ofNullable(dto.getAirCompanyTypeId())
                .map(airCompanyTypeDao::findByIdExpected)
                .ifPresent(airCompany::setAirCompanyType);
        ofNullable(dto.getName()).ifPresent(airCompany::setName);
        if (isOneOfParamsIsPresent(dto)) {
            throwExceptionIfRecordIsPresent(airCompany);
            saveOrUpdate(airCompany);
        }
        return airCompany;
    }

    private boolean isOneOfParamsIsPresent(AirCompanyDTO.CreateUpdate dto) {
        return Stream.of(dto.getName(), dto.getAirCompanyTypeId()).anyMatch(Objects::nonNull);
    }

    private void throwExceptionIfRecordIsPresent(AirCompany airCompany) {
        if (getByNameAndAirCompanyTypeId(airCompany.getName(), airCompany.getAirCompanyType().getId()).isPresent())
            throw new EntityExistsException(format("AirCompany with name: %s and type: %s already exists.",
                    airCompany.getName(), airCompany.getAirCompanyType().getName()));
    }

}

