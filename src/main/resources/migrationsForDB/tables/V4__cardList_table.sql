 CREATE TABLE cardLists
(
    id           uuid PRIMARY KEY NOT NULL,
    created_by   VARCHAR(100)     NOT NULL,
    updated_by   VARCHAR(100),
    created_date TIMESTAMP        NOT NULL,
    updated_date TIMESTAMP,
    name         VARCHAR(30)      NOT NULL,
    archived     BOOLEAN          NOT NULL,
    board_id     uuid             NOT NULL
        CONSTRAINT cardList_board_id_fk REFERENCES boards (id)
);