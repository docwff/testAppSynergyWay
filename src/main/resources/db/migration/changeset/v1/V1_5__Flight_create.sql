CREATE TABLE Flight
(
    id                   BIGINT                              NOT NULL PRIMARY KEY AUTO_INCREMENT,
    airplaneId           BIGINT                              NOT NULL,
    flightStatusId       BIGINT                              NOT NULL,
    destinationCountryId BIGINT                              NOT NULL,
    departureCountryId   BIGINT                              NOT NULL,
    distance             INTEGER                             NOT NULL,
    estimatedFlightTime  TIME                                NOT NULL,
    departure            DATETIME                            NULL,
    arrived              DATETIME                            NULL,
    delayStartedAt       DATETIME                            NULL,
    createdAt            TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
) ENGINE = InnoDB;


ALTER TABLE Flight
    ADD CONSTRAINT fk_Flight_airplaneId_to_Airplane_id
        FOREIGN KEY (airplaneId) REFERENCES Airplane (id);

ALTER TABLE Flight
    ADD CONSTRAINT fk_Flight_departureCountryId_to_Country_id
        FOREIGN KEY (departureCountryId) REFERENCES Country (id);

ALTER TABLE Flight
    ADD CONSTRAINT fk_Flight_destinationCountryId_to_Country_id
        FOREIGN KEY (destinationCountryId) REFERENCES Country (id);

ALTER TABLE Flight
    ADD CONSTRAINT fk_Flight_flightStatusId_to_FlightStatus_id
        FOREIGN KEY (flightStatusId) REFERENCES FlightStatus (id);

