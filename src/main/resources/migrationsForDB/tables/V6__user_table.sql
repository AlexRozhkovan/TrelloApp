CREATE TABLE users
(
    id         uuid PRIMARY KEY NOT NULL,
    first_name VARCHAR(20)      NOT NULL,
    last_name  VARCHAR(20)      NOT NULL,
    email      VARCHAR(70)      NOT NULL,
    created_date TIMESTAMP      NOT NULL,
    updated_date TIMESTAMP
);
