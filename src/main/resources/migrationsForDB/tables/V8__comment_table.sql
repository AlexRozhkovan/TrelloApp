CREATE TABLE comments (
                         id uuid PRIMARY KEY,
                         created_by uuid,
                         updated_by uuid,
                         created_date TIMESTAMP,
                         updated_date TIMESTAMP,
                         text VARCHAR(200) NOT NULL,
                         card_id uuid NOT NULL CONSTRAINT comment_card_id_fk REFERENCES cards (id)
);