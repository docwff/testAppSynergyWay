package com.api.testappsynergyway.dao.airplane;

import com.api.testappsynergyway.dao.util.AbstractDaoImpl;
import com.api.testappsynergyway.dao.util.AbstractRepository;
import com.api.testappsynergyway.entity.Airplane;
import org.springframework.stereotype.Service;

@Service
 class AirplaneDaoImpl extends AbstractDaoImpl<Airplane, Long> implements AirplaneDao {

    final AirplaneRepository airplaneRepository;

    public AirplaneDaoImpl(
            AbstractRepository<Airplane, Long> abstractRepository,
            AirplaneRepository airplaneRepository) {
        super(abstractRepository);
        this.airplaneRepository = airplaneRepository;
    }


}

