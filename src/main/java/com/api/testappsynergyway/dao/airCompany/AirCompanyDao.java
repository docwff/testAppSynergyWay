package com.api.testappsynergyway.dao.airCompany;

import com.api.testappsynergyway.dao.util.AbstractDao;
import com.api.testappsynergyway.entity.AirCompany;

public interface AirCompanyDao extends AbstractDao<AirCompany, Long> {

    AirCompany findByNameAndAirCompanyTypeId(String name, Long airCompanyTypeId);

}
