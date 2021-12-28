CREATE TABLE cards (
                      id uuid PRIMARY KEY,
                      created_by uuid,
                      updated_by uuid,
                      created_date TIMESTAMP,
                      updated_date TIMESTAMP,
                      name VARCHAR(30) NOT NULL,
                      description VARCHAR(100) NOT NULL,
                      archived BOOLEAN NOT NULL,
                      cardList_id uuid CONSTRAINT card_cardList_id_fk references cardLists (id)
);