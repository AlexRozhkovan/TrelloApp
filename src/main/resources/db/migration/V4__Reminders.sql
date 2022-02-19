create table reminders
(
    id        uuid    not null
            primary key,
    active    boolean not null default false,
    finish    timestamp,
    remind_on timestamp,
    start     timestamp
);