CREATE TABLE cards
(
    id           uuid PRIMARY KEY NOT NULL,
    created_by   VARCHAR(100)     NOT NULL,
    updated_by   VARCHAR(100),
    created_date TIMESTAMP        NOT NULL,
    updated_date TIMESTAMP,
    name         VARCHAR(30)      NOT NULL,
    description  VARCHAR(100),
    archived     BOOLEAN          NOT NULL,
    cardList_id  uuid
        CONSTRAINT card_cardList_id_fk references cardLists (id)
);