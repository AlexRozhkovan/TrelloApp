create table users
(
    id           uuid         not null

            primary key,
    created_by   varchar(255),
    created_date timestamp    not null,
    updated_by   varchar(255),
    updated_date timestamp,
    email        varchar(255) not null,
    first_name   varchar(255) not null,
    last_name    varchar(255) not null
);