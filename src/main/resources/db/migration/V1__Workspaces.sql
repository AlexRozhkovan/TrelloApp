create table workspaces
(
    id           uuid         not null
            primary key,
    created_by   varchar(255) not null,
    created_date timestamp    not null,
    updated_by   varchar(255),
    updated_date timestamp,
    description  varchar(255),
    name         varchar(255) not null,
    visibility   varchar(255) not null
);