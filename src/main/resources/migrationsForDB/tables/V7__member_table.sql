CREATE TABLE members
(
    id           uuid PRIMARY KEY NOT NULL,
    created_by   VARCHAR(100)     NOT NULL,
    updated_by   VARCHAR(100),
    created_date TIMESTAMP        NOT NULL,
    updated_date TIMESTAMP,
    role         VARCHAR(10)      NOT NULL,
    user_id      uuid
        CONSTRAINT member_user_id_fk REFERENCES users (id)
);