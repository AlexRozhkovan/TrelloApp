CREATE TABLE workspaces
(
    id           uuid PRIMARY KEY NOT NULL,
    created_by   VARCHAR(100)     NOT NULL,
    updated_by   VARCHAR(100),
    created_date TIMESTAMP        NOT NULL,
    updated_date TIMESTAMP,
    name         VARCHAR(30)      NOT NULL,
    description  VARCHAR(100),
    visibility   VARCHAR(10)      NOT NULL
);