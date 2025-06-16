CREATE TABLE stroll (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    latitude DOUBLE,
    longitude DOUBLE
);

CREATE TABLE stroll_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255),
    role VARCHAR(255)
);
