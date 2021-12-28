CREATE TABLE checkLists (
                           id uuid PRIMARY KEY,
                           created_by uuid,
                           updated_by uuid,
                           created_date TIMESTAMP,
                           updated_date TIMESTAMP,
                           name VARCHAR(30)
);