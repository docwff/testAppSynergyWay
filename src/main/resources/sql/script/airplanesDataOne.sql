INSERT INTO Airplane (id, name, airCompanyId, factoryId, flightDistance, numberOfFlights, fuelCapacity, serialNumber, airplaneTypeId, createdAt)
VALUES  (1, 'Lockheed C-66', 8, 45, 5600, 32, 24636, 'FG2CV3', 1, '2022-01-05'),
        (2, 'Airbus A340', 2, 65, 7450, 663, 78635, 'KOL4352', 2, '2022-01-05'),
        (3, 'Airbus A310', NULL, 32, 8900, 23, 45674, 'KOIL2414', 9, '2022-01-05'),
        (4, 'Embraer E190', 5, 89, 2400, 279, 52563, 'IOSD5244', 12, '2022-01-05'),
        (5, 'Airbus A340', 7, 43, 16000, 652, 43552, 'OPLK2352', 14, '2022-01-05'),
        (6, 'Boeing 787', 2, 89, 8000, 342, 110000, 'NAPS5251', 3, '2022-01-05'),
        (7, 'Airbus 257', 2, 89, 7200, 156, 105000, 'JUES2524', 1, '2021-12-12'),
        (8, 'Airbus 11/2', 2, 178, 18000, 235, 195600, 'KOUS2535', 12, '1994-09-09'),
        (9, 'Boeing 123', 2, 25, 11000, 15, 78900, 'NVK25252', 9, '2010-05-26')
ON DUPLICATE KEY
    UPDATE id             = VALUES(id),
           NAME           = VALUES(NAME),
           airCompanyId   = VALUES(airCompanyId),
           factoryId      = VALUES(factoryId),
           flightDistance = VALUES(flightDistance),
           numberOfFlights   = VALUES(numberOfFlights),
           fuelCapacity   = VALUES(fuelCapacity),
           serialNumber   = VALUES(serialNumber),
           airplaneTypeId = VALUES(airplaneTypeId),
           createdAt      = VALUES(createdAt);
