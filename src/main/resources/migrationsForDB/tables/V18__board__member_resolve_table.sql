CREATE TABLE boards_members
(
    board_id   uuid NOT NULL
        CONSTRAINT board_member_card_id_fk REFERENCES boards (id),
    member_id uuid NOT NULL
        CONSTRAINT board_member_member_id_fk REFERENCES members (id),
    CONSTRAINT  board_member_pk PRIMARY KEY (board_id, member_id)
);