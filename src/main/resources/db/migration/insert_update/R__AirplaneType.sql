INSERT INTO AirplaneType (id, name)
VALUES (1, 'Very Light Jet'),
       (2, 'Light Business Jet'),
       (3, 'Mid-Sized Private Jet'),
       (4, 'Heavy Business Jet'),
       (5, 'Military Jet'),
       (6, 'Private Single Engine'),
       (7, 'Twin Turbo Propeller Plane'),
       (8, 'Military Turbo Propeller'),
       (9, 'Amphibious'),
       (10, 'Aerobatic Plane'),
       (11, 'Light Passenger Jet'),
       (12, 'Mid-size Passenger Jet'),
       (13, 'Passenger Turboprop'),
       (14, 'Cargo Airplane'),
       (15, 'Jumbo Passenger Jet')

ON DUPLICATE KEY
    UPDATE id   = VALUES(id),
           NAME = VALUES(NAME);


