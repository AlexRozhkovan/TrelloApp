create table members
(
    id           uuid         not null
            primary key,
    created_by   varchar(255) not null,
    created_date timestamp    not null,
    updated_by   varchar(255),
    updated_date timestamp,
    role         varchar(255) not null,
    user_id      uuid         not null
);