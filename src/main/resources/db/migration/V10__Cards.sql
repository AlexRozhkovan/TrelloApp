create table cards
(
    id           uuid         not null
        primary key,
    created_by   varchar(255) not null,
    created_date timestamp    not null,
    updated_by   varchar(255),
    updated_date timestamp,
    archived     boolean      not null,
    card_list_id uuid         not null,
    description  varchar(255),
    name         varchar(255) not null,
    reminder_id  uuid
        references reminders
);