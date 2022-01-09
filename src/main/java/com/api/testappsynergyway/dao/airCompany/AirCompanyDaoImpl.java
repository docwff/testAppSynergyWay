package com.api.testappsynergyway.dao.airCompany;

import com.api.testappsynergyway.dao.util.AbstractDaoImpl;
import com.api.testappsynergyway.dao.util.AbstractRepository;
import com.api.testappsynergyway.entity.AirCompany;
import org.springframework.stereotype.Service;

@Service
class AirCompanyDaoImpl extends AbstractDaoImpl<AirCompany, Long> implements AirCompanyDao {

   final AirCompanyRepository airCompanyRepository;

    public AirCompanyDaoImpl(
            AbstractRepository<AirCompany, Long> abstractRepository,
            AirCompanyRepository airCompanyRepository) {
        super(abstractRepository);
        this.airCompanyRepository = airCompanyRepository;
    }


    @Override
    public AirCompany findByNameAndAirCompanyTypeId(String name, Long airCompanyTypeId) {
        return airCompanyRepository.findByNameAndAirCompanyTypeId(name, airCompanyTypeId);
    }
}
