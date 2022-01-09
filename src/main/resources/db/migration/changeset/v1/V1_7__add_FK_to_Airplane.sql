ALTER TABLE Airplane
    CHANGE type airplaneTypeId BIGINT NOT NULL;

ALTER TABLE Airplane
    ADD CONSTRAINT fk_Airplane_airplaneTypeId_to_AirplaneType_id
        FOREIGN KEY (airplaneTypeId) REFERENCES AirplaneType (id)
