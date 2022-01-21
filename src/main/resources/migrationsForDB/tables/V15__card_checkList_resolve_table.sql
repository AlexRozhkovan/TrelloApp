CREATE TABLE cards_checklists
(
    card_id      uuid NOT NULL
        CONSTRAINT card_checklist_card_id_fk REFERENCES cards (id),
    checklist_id uuid NOT NULL
        CONSTRAINT card_checklist_checklist_id_fk REFERENCES checkLists (id),
    CONSTRAINT card_checklist_pk PRIMARY KEY (card_id, checklist_id)
);