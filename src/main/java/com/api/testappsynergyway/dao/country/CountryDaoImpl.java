package com.api.testappsynergyway.dao.country;

import com.api.testappsynergyway.dao.util.AbstractDaoImpl;
import com.api.testappsynergyway.dao.util.AbstractRepository;
import com.api.testappsynergyway.entity.Country;
import org.springframework.stereotype.Service;

@Service
class CountryDaoImpl extends AbstractDaoImpl<Country, Long> implements CountryDao {

    final CountryRepository countryRepository;

    public CountryDaoImpl(
            AbstractRepository<Country, Long> abstractRepository,
            CountryRepository countryRepository) {
        super(abstractRepository);
        this.countryRepository = countryRepository;
    }


}
