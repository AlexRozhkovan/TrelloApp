CREATE TABLE attachments
(
    id           uuid PRIMARY KEY NOT NULL,
    created_by   VARCHAR(100)     NOT NULL,
    updated_by   VARCHAR(100),
    created_date TIMESTAMP        NOT NULL,
    updated_date TIMESTAMP,
    name         VARCHAR(30),
    link         VARCHAR(100),
    file         bytea,
    card_id      uuid
        CONSTRAINT attachment_card_id_fk REFERENCES cards (id),
    comment_id   uuid
        CONSTRAINT attachment_comment_id_fk REFERENCES comments (id)
);