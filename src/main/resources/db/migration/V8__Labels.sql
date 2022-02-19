create table labels
(
    id           uuid         not null
            primary key,
    created_by   varchar(255) not null,
    created_date timestamp    not null,
    updated_by   varchar(255),
    updated_date timestamp,
    card_id      uuid         not null,
    color        varchar(255) not null,
    name         varchar(255) not null
);