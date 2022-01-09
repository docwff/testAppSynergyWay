package com.api.testappsynergyway.dao.airCompany;

import com.api.testappsynergyway.dao.util.AbstractRepository;
import com.api.testappsynergyway.entity.AirCompany;
import org.springframework.stereotype.Repository;

@Repository
interface AirCompanyRepository extends AbstractRepository<AirCompany, Long> {

    AirCompany findByNameAndAirCompanyTypeId(String name, Long airCompanyTypeId);
}
