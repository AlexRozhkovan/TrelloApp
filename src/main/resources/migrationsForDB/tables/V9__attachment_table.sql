CREATE TABLE attachments (
                            id uuid PRIMARY KEY,
                            created_by uuid,
                            updated_by uuid,
                            created_date TIMESTAMP,
                            updated_date TIMESTAMP,
                            name VARCHAR(30),
                            link VARCHAR(100),
                            file bytea,
                            card_id uuid CONSTRAINT attachment_card_id_fk REFERENCES cards (id),
                            comment_id uuid CONSTRAINT attachment_comment_id_fk REFERENCES comments (id)
);