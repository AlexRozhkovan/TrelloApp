CREATE TABLE cards_comments
(
    card_id   uuid NOT NULL
        CONSTRAINT card_comment_card_id_fk REFERENCES cards (id),
    comment_id uuid NOT NULL
        CONSTRAINT comment_card_card_id_fk REFERENCES comments (id),
    CONSTRAINT comment_card_pk PRIMARY KEY (card_id, comment_id)
);