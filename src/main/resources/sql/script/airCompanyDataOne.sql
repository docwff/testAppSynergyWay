INSERT INTO AirCompany (id, name, airCompanyTypeId, foundedAt)
VALUES (1, 'Singapore Airlines', 1, '2014-06-08'),
       (2, 'Japan Airlines', 2, '2004-05-19'),
       (3, 'Wizz Air', 3, '1990-11-12'),
       (4, 'Ryanair', 2, '1999-07-12'),
       (5, 'KLM', 3, '1978-04-23'),
       (6, 'British Airways', 3, '1974-12-19'),
       (7, 'Turkish Airlines', 2, '2007-07-22'),
       (8, 'Lufthansa', 2, '1991-09-10')

ON DUPLICATE KEY
    UPDATE id               = VALUES(id),
           NAME             = VALUES(NAME),
           airCompanyTypeId = VALUES(airCompanyTypeId),
           foundedAt        = VALUES(foundedAt);