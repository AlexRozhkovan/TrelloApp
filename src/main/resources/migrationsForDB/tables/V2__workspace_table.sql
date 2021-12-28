CREATE TABLE workspaces (
                           id uuid PRIMARY KEY,
                           name VARCHAR(30) NOT NULL,
                           description VARCHAR(100) NOT NULL,
                           visibility VARCHAR(10) NOT NULL
);