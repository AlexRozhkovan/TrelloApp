create table checkable_items
(
    id      uuid         not null
            primary key,
    checked boolean      not null,
    name    varchar(255) not null
);