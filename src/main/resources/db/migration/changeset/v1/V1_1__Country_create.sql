CREATE TABLE Country
(
    id   BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    iso  VARCHAR(2),
    iso3 VARCHAR(3),
    name VARCHAR(100)
) ENGINE = InnoDB;

ALTER TABLE Country
    ADD CONSTRAINT UKmc3vma1oo3f1ukskrh35dror2 UNIQUE (name, iso, iso3)
