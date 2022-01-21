CREATE TABLE workspaces_members
(
    workspace_id uuid NOT NULL
        CONSTRAINT workspace_member_workspace_id_fk REFERENCES workspaces (id),
    member_id    uuid NOT NULL
        CONSTRAINT workspace_member_member_id_fk REFERENCES members (id),
    CONSTRAINT workspace_member_pk PRIMARY KEY (workspace_id, member_id)
);