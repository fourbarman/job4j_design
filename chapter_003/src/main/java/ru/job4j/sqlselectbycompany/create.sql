/* Author: fourbarman */

/*
1. Таблица company

CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

2. Таблица person

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer,
    CONSTRAINT person_pkey PRIMARY KEY (id)
);
*/

/*
Создать таблицы и заполнить их начальными данными
 */
CREATE TABLE company
(
    id   integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id         integer NOT NULL,
    name       character varying,
    company_id integer,
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company (name)
values ('First Company'),
       ('Second Company'),
       ('Third Company'),
       ('Fourth Company'),
       ('Fifth Company'),
       ('Sixth Company'),
       ('Seventh Company'),
       ('Eighth Company'),
       ('Ninth Company'),
       ('Tenth Company')
;

insert into person (name, company_id)
values ('person 1', 1),
       ('person 2', 2),
       ('person 3', 3),
       ('person 4', 4),
       ('person 5', 5),
       ('person 6', 6),
       ('person 7', 7),
       ('person 8', 8),
       ('person 9', 9),
       ('person 10', 10),
       ('person 11', 1),
       ('person 12', 2),
       ('person 13', 2),
       ('person 14', 2),
       ('person 15', 3),
       ('person 16', 4),
       ('person 17', 5)
;

