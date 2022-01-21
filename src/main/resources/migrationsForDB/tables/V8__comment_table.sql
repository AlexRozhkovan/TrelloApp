CREATE TABLE comments
(
    id           uuid PRIMARY KEY NOT NULL,
    created_by   VARCHAR(100)     NOT NULL,
    updated_by   VARCHAR(100),
    created_date TIMESTAMP        NOT NULL,
    updated_date TIMESTAMP,
    text         VARCHAR(200)     NOT NULL,
    card_id      uuid             NOT NULL
        CONSTRAINT comment_card_id_fk REFERENCES cards (id)
);