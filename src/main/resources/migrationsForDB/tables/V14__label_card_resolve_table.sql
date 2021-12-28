CREATE TABLE labels_cards (
                            card_id uuid NOT NULL CONSTRAINT label_card_card_id_fk REFERENCES cards (id),
                            label_id uuid NOT NULL CONSTRAINT label_card_label_id_fk REFERENCES labels (id),
                            CONSTRAINT label_card_pk PRIMARY KEY (card_id,label_id)
);