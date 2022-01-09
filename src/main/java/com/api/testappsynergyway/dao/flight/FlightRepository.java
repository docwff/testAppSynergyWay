package com.api.testappsynergyway.dao.flight;

import com.api.testappsynergyway.dao.util.AbstractRepository;
import com.api.testappsynergyway.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
interface FlightRepository extends AbstractRepository<Flight, Long> {

    @Query(value = "SELECT * FROM Flight F \n" +
            "WHERE airplaneId IN (SELECT A.id FROM Airplane A WHERE A.id = airplaneId AND A.airCompanyId = ?1) \n" +
            "AND flightStatusId = ?2 ", nativeQuery = true)
    List<Flight> findByCompanyIdAndStatusId(Long airCompanyId, Long flightStatusId);

    @Query(value = "SELECT * FROM Flight f WHERE flightStatusId = 1 AND departure <= NOW() - INTERVAL 1 DAY", nativeQuery = true)
    Page<Flight> findActiveFlightsThatDepartedDayAgo(Pageable pageable);

    @Query(value = " SELECT AC.name                                                                      AS company,\n" +
            "       DEPAC.name                                                                           AS depatrureCountry,\n" +
            "       DESTC.name                                                                           AS destinationCountry,\n" +
            "       estimatedFlightTime                                                                AS estimatedFlightTime,\n" +
            "       SEC_TO_TIME(TIMESTAMPDIFF(SECOND, departure, arrived))                           AS actualFlightTime,\n" +
            "       DATE_FORMAT(departure, '%Y-%m-%d %H:%i:%s')                                        AS departureAt,\n" +
            "       DATE_FORMAT(arrived, '%Y-%m-%d %H:%i:%s')                                          AS arrivedAt,\n" +
            "       SEC_TO_TIME(TIMESTAMPDIFF(SECOND, ADDTIME(departure, estimatedFlightTime), arrived)) AS delayTime\n" +
            "FROM Flight \n" +
            "          JOIN Airplane A ON airplaneId = A.id\n" +
            "          JOIN AirCompany AC ON A.airCompanyId = AC.id\n" +
            "          JOIN Country DESTC ON destinationCountryId = DESTC.id\n" +
            "          JOIN Country DEPAC ON DEPAC.id = departureCountryId\n" +
            "WHERE flightStatusId = 2\n" +
            "  AND departure IS NOT NULL\n" +
            "  AND arrived IS NOT NULL\n" +
            "  AND TIMESTAMPDIFF(SECOND, arrived, ADDTIME(departure, estimatedFlightTime)) < 0 ",
            countQuery = "select count(*) FROM Flight \n" +
                    "WHERE flightStatusId = 2\n" +
                    "  AND departure IS NOT NULL\n" +
                    "  AND arrived IS NOT NULL\n" +
                    "  AND TIMESTAMPDIFF(SECOND, arrived, ADDTIME(departure, estimatedFlightTime)) < 0 ",
            nativeQuery = true)
    Page<Map<String, Object>> findCompletedFlightsWithDelay(Pageable pageable);

}
