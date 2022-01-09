package com.api.testappsynergyway.dao.airplane;

import com.api.testappsynergyway.dao.util.AbstractRepository;
import com.api.testappsynergyway.entity.Airplane;
import org.springframework.stereotype.Repository;

@Repository
interface AirplaneRepository extends AbstractRepository<Airplane, Long> {
}
