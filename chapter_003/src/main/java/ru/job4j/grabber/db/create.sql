/* Author: fourbarman */

/*
 * Create tables in grabber database.
 */

create table post
(
    id      serial primary key not null,
    name    varchar(2000),
    text    text,
    link    text CONSTRAINT unique_link UNIQUE,
    created timestamp
);

create table parse_time
(
  id serial primary key not null,
  parse_timestamp timestamp
);