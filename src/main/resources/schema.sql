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

CREATE TABLE stroll_transaction_log (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    transaction_type VARCHAR(255),
    transaction_result VARCHAR(255),
    transaction_timestamp TIMESTAMP
);

CREATE TABLE stroll_user_comment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    user_comment VARCHAR(255) NOT NULL,
    comment_timestamp TIMESTAMP
);

