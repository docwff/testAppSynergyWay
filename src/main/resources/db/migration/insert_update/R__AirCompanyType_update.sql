INSERT INTO AirCompanyType (id, name)
VALUES (1, 'Major'),
       (2, 'National'),
       (3, 'Regional')

ON DUPLICATE KEY UPDATE id   = VALUES(id),
                        name = VALUES(name);



