CREATE TABLE Airplane
(
    id              BIGINT                      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name            VARCHAR(100)                NOT NULL,
    airCompanyId    BIGINT                      NULL,
    factoryId       BIGINT                      NOT NULL,
    flightDistance  INTEGER                     NOT NULL,
    numberOfFlights INTEGER                     NOT NULL,
    fuelCapacity    INTEGER                     NOT NULL,
    serialNumber    VARCHAR(15)                 NOT NULL,
    type            BIGINT                      NOT NULL,
    createdAt       DATE DEFAULT (CURRENT_DATE) NOT NULL
) ENGINE = InnoDB;

ALTER TABLE Airplane
    ADD CONSTRAINT fk_Airplane_airCompanyId_to_AirCompany_id
        FOREIGN KEY (airCompanyId) REFERENCES AirCompany (id);

ALTER TABLE Airplane
    ADD CONSTRAINT fk_Airplane_factoryId_to_Country_id
        FOREIGN KEY (factoryId) REFERENCES Country (id);




