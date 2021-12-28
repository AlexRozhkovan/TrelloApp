CREATE TABLE checkable_items (
                                id uuid PRIMARY KEY,
                                name VARCHAR(30),
                                checked BOOLEAN NOT NULL,
                                checklist_id uuid NOT NULL CONSTRAINT checkable_item_checklist_id_fk REFERENCES checklists (id)
);