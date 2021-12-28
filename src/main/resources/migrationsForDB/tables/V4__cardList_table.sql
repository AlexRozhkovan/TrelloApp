CREATE TABLE cardLists (
                          id uuid PRIMARY KEY,
                          created_by uuid,
                          updated_by uuid,
                          created_date TIMESTAMP,
                          updated_date TIMESTAMP,
                          name VARCHAR(30) NOT NULL,
                          archived BOOLEAN NOT NULL,
                          board_id uuid NOT NULL CONSTRAINT cardList_board_id_fk REFERENCES boards (id)
);