CREATE TABLE members (
    id uuid PRIMARY KEY,
    created_by uuid,
    updated_by uuid,
    created_date TIMESTAMP,
    updated_date TIMESTAMP,
    role VARCHAR(10) NOT NULL,
    user_id uuid CONSTRAINT member_user_id_fk REFERENCES users (id)
);