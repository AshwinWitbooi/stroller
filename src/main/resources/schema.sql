CREATE TABLE stroll (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    latitude DOUBLE,
    longitude DOUBLE
);
