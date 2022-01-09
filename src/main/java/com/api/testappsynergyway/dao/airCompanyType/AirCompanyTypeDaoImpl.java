package com.api.testappsynergyway.dao.airCompanyType;

import com.api.testappsynergyway.dao.util.AbstractDaoImpl;
import com.api.testappsynergyway.dao.util.AbstractRepository;
import com.api.testappsynergyway.entity.AirCompanyType;
import org.springframework.stereotype.Service;

@Service
class AirCompanyTypeDaoImpl extends AbstractDaoImpl<AirCompanyType, Long> implements AirCompanyTypeDao {

    final AirCompanyTypeRepository airCompanyTypeRepository;

    public AirCompanyTypeDaoImpl(AbstractRepository<AirCompanyType, Long> abstractRepository,
                                 AirCompanyTypeRepository airCompanyTypeRepository) {
        super(abstractRepository);
        this.airCompanyTypeRepository = airCompanyTypeRepository;
    }
}
