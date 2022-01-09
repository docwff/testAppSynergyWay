INSERT INTO FlightStatus (id, name)
VALUES (1, 'ACTIVE'),
       (2, 'COMPLETED'),
       (3, 'DELAYED'),
       (4, 'PENDING')

ON DUPLICATE KEY UPDATE id   = VALUES(id),
                        name = VALUES(name);
          


