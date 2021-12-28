CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE boards (
                       id uuid PRIMARY KEY,
                       created_by uuid,
                       updated_by uuid,
                       created_date TIMESTAMP,
                       updated_date TIMESTAMP,
                       name VARCHAR (30) NOT NULL,
                       description VARCHAR (100) NOT NULL,
                       visibility VARCHAR (10) NOT NULL,
                       archived BOOLEAN NOT NULL,
                       workspace_id uuid CONSTRAINT board_workspace_id_fk references workspaces (id)
);