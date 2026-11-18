CREATE DATABASE IF NOT EXISTS employment_db;
USE employment_db;

DROP TABLE IF EXISTS applications;

CREATE TABLE applications (
    id                    INT AUTO_INCREMENT PRIMARY KEY,
    full_name             VARCHAR(100) NOT NULL,
    address               VARCHAR(255),
    contact_number        VARCHAR(20),
    email                 VARCHAR(100),
    education             VARCHAR(100),
    gender                VARCHAR(10),
    date_available        DATE,
    desired_position      VARCHAR(100),
    desired_salary        VARCHAR(50),
    authorized_to_work    BOOLEAN,
    has_relatives         BOOLEAN,
    relatives_explanation VARCHAR(255),
    signature             VARCHAR(100),
    submitted_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

SELECT * FROM applications;