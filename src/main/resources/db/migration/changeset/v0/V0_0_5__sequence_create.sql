CREATE TABLE hibernate_sequence
(
    next_val BIGINT
) ENGINE = InnoDB;

INSERT INTO hibernate_sequence (next_val) VALUE (1000);