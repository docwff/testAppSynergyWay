CREATE TABLE AirCompany
(
    id               BIGINT                      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name             VARCHAR(120)                NOT NULL,
    airCompanyTypeId BIGINT                      NOT NULL,
    foundedAt        DATE DEFAULT (CURRENT_DATE) NOT NULL
) ENGINE = InnoDB;

ALTER TABLE AirCompany
    ADD CONSTRAINT fk_AirCompany_companyTypeId_to_AirCompanyType_id
        FOREIGN KEY (airCompanyTypeId) REFERENCES AirCompanyType (id);

CREATE UNIQUE INDEX uk_AirCompany_columns_name_and_companyTypeId
    ON AirCompany (name, airCompanyTypeId);
