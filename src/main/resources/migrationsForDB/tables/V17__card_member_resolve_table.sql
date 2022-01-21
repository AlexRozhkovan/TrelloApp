CREATE TABLE cards_members
(
    card_id   uuid NOT NULL
        CONSTRAINT card_member_card_id_fk REFERENCES cards (id),
    member_id uuid NOT NULL
        CONSTRAINT card_member_member_id_fk REFERENCES members (id),
    CONSTRAINT card_member_pk PRIMARY KEY (card_id, member_id)
);