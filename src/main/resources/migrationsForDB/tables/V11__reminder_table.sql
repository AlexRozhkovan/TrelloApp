CREATE TABLE reminders (
                          id uuid PRIMARY KEY,
                          start TIMESTAMP,
                          "end" TIMESTAMP,
                          remind_on TIMESTAMP,
                          active BOOLEAN NOT NULL,
                          card_id uuid NOT NULL CONSTRAINT reminder_card_id_fk REFERENCES cards (id)
);