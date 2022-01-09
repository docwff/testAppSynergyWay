package com.api.testappsynergyway.dao.airplaneType;

import com.api.testappsynergyway.dao.util.AbstractDaoImpl;
import com.api.testappsynergyway.dao.util.AbstractRepository;
import com.api.testappsynergyway.entity.AirplaneType;
import org.springframework.stereotype.Service;

@Service
class AirplaneTypeDaoImpl extends AbstractDaoImpl<AirplaneType, Long> implements AirplaneTypeDao {

    final AirplaneTypeRepository airplaneTypeRepository;

    public AirplaneTypeDaoImpl(
            AbstractRepository<AirplaneType, Long> abstractRepository,
            AirplaneTypeRepository airplaneTypeRepository) {
        super(abstractRepository);
        this.airplaneTypeRepository = airplaneTypeRepository;
    }
}
