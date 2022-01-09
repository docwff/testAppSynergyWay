package com.api.testappsynergyway.dao.country;

import com.api.testappsynergyway.dao.util.AbstractRepository;
import com.api.testappsynergyway.entity.Country;
import org.springframework.stereotype.Repository;

@Repository
interface CountryRepository extends AbstractRepository<Country, Long> {
}
