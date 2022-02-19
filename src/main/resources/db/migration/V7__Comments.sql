create table comments
(
    id           uuid         not null
            primary key,
    created_by   varchar(255) not null,
    created_date timestamp    not null,
    updated_by   varchar(255),
    updated_date timestamp,
    card_id      uuid         not null,
    text         varchar(255) not null,
    user_id      uuid         not null
);