create table attachments
(
    id      uuid not null
        primary key,
    card_id uuid,
    context varchar,
    name    varchar(255),
    file    bytea
);