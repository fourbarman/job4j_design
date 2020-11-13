/* Author: fourbarman */

/*
Создать структуры данных в базе.
Таблицы:
Кузов. Двигатель, Коробка передач.
Создать структуру Машина. Машина не может существовать без данных из п.1.
Заполнить таблицы через insert.

Создать SQL запросы:
1. Вывести список всех машин и все привязанные к ним детали.
2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
*/

/*
Создать таблицы и заполнить их начальными данными
 */

create database car_storage;

create table engine
(
    id   serial primary key,
    name varchar(255)
);
--
create table body
(
    id   serial primary key,
    name varchar(255)
);
--
create table transmission
(
    id   serial primary key,
    name varchar(255)
);
--
create table cars
(
    id              serial primary key,
    name            VARCHAR(255),
    engine_id       int references engine (id)       not null,
    body_id         int references body (id)         not null,
    transmission_id int references transmission (id) not null
);
--
insert into engine (name)
values ('Heat engine'),
       ('Non-thermal chemically powered motor'),
       ('Electric motor'),
       ('Physically powered motor');
--
insert into body (name)
values ('Microcar'),
       ('A-segment'),
       ('B-segment'),
       ('C-segment'),
       ('D-segment'),
       ('F-segment');
--
insert into transmission (name)
values ('Manual'),
       ('Automatic'),
       ('Semi-automatic'),
       ('Sequential manual');
--
insert into cars (name, engine_id, body_id, transmission_id)
values ('Smart ForTwo', 3, 1, 2),
       ('Honda Life', 1, 1, 2),
       ('Toyota Auris', 1, 4, 1),
       ('Chevrolet Impala', 1, 6, 2);

/*
Вывести список всех машин и все привязанные к ним детали.
 */

select c.name, e.name, b.name, t.name
from cars c,
     engine e,
     body b,
     transmission t
where engine_id = e.id
  and body_id = b.id
  and transmission_id = t.id;

/*
Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
 */
select *
from (select engine.name
      from engine
               left join cars c on engine.id = c.engine_id
      where c.name is null) part
union
(select body.name
 from body
          left join cars c on body.id = c.body_id
 where c.name is null)
union
(select transmission.name
 from transmission
          left join cars c on transmission.id = c.body_id
 where c.name is null)
order by name;