create table boards_members
(
    board_id  uuid not null
            references boards,
    member_id uuid
);

create table cards_check_lists
(
    card_id        uuid not null
            references cards,
    check_lists_id uuid not null
            unique
            references check_lists
);

create table cards_members
(
    card_id   uuid not null
            references cards,
    member_id uuid
);

create table check_lists_items
(
    check_list_id uuid not null
            references check_lists,
    items_id      uuid not null
            unique
            references checkable_items
);

create table workspaces_members
(
    workspace_id uuid not null
            references workspaces,
    member_id    uuid
);