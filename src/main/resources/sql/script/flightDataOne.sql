INSERT INTO Flight (id, airplaneId, flightStatusId, destinationCountryId, departureCountryId, distance, estimatedFlightTime, departure, arrived, delayStartedAt, createdAt)
VALUES  (1, 2, 2, 45, 64, 3424525, '00:56:00', '2020-01-05 19:00:21', '2020-01-05 19:58:31', NULL, '2022-01-05 19:02:31'),
        (2, 6, 4, 42, 24, 4525, '01:25:00', NULL, NULL, NULL, '2022-01-05 19:02:31'),
        (3, 4, 3, 63, 122, 53552, '08:25:00', '2017-10-05 15:00:25', '2022-11-10 19:00:32', '2017-10-05 15:11:11', '2022-08-05 19:02:31'),
        (4, 1, 2, 200, 41, 235234, '06:00:00', '2020-01-05 20:56:07', '2020-01-06 01:56:10', NULL, '2022-01-05 20:57:20'),
        (5, 4, 3, 156, 12, 234525, '02:14:00', '2019-05-27 20:53:38',  NULL, '2019-05-27 21:11:42', '2022-01-05 20:57:21'),
        (6, 1, 1, 125, 90, 2352344, '01:48:00', NULL, NULL, NULL, '2022-01-05 20:57:23'),
        (7, 9, 1, 67, 156, 12444, '03:20:22', '2018-11-19 15:00:00', NULL, NULL, '2022-01-01 06:23:31'),
        (8, 8, 1, 89, 156, 756463, '14:35:00', '2018-04-18 07:45:25', NULL, NULL, '2022-01-02 14:02:31'),
        (9, 7, 1, 52, 23, 536346, '01:35:00', '2018-03-15 04:25:25', NULL, NULL, '2022-01-06 19:19:31'),
        (10, 5, 2, 101, 103, 2345245, '01:00:00', '2018-03-15 07:05:34', '2018-03-15 08:35:34', '2018-03-15 07:15:34', '2021-05-12 11:45:31'),
        (11, 2, 2, 195, 51, 14000, '03:15:00', '2020-10-10 10:30:34', '2020-10-10 14:50:11', '2020-10-10 11:15:34', '2020-10-10 17:45:31')

ON DUPLICATE KEY
    UPDATE id                   = VALUES(id),
           airplaneId           = VALUES(airplaneId),
           flightStatusId       = VALUES(flightStatusId),
           destinationCountryId = VALUES(destinationCountryId),
           departureCountryId   = VALUES(departureCountryId),
           distance             = VALUES(distance),
           estimatedFlightTime  = VALUES(estimatedFlightTime),
           departure            = VALUES(departure),
           arrived              = VALUES(arrived),
           delayStartedAt       = VALUES(delayStartedAt),
           createdAt            = VALUES(createdAt);

