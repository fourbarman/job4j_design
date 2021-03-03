/* Author: fourbarman */

/*
 * Create table in grabber database.
 */

create table post
(
    id      serial primary key not null,
    name    varchar(2000),
    text    text,
    link    text CONSTRAINT unique_link UNIQUE,
    created timestamp
);