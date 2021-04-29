/* Author: fourbarman */
--create database meetings
create database meetings;

-- create tables
-- table users
create table users
(
id serial not null,
name CHARACTER VARYING,
constraint users_pkey primary key (id)
);

-- table meetings
create table meetings
(
id SERIAL NOT NULL,
name CHARACTER VARYING,
CONSTRAINT meet_pkey PRIMARY KEY (id)
);

-- table status
create table status
(
id SERIAL NOT NULL,
name CHARACTER VARYING,
CONSTRAINT status_pkey PRIMARY KEY (id)
);

-- table requests
create table requests
(
id SERIAL NOT NULL,
user_id INTEGER REFERENCES users (id),
meet_id INTEGER REFERENCES meetings (id),
status_id INTEGER REFERENCES status (id)
);

--Fill tables.
--fill users
insert into users (name)
values ('Max'),
('Ivan'),
('Petr'),
('Andrey'),
('Victor'),
('Boris'),
('Michail')
('Alexander');

-- fill meetings
insert into meetings (name)
values ('QA EVENING'),
('TeamLead Conf 2021'),
('UX in E-commerce'),
('DEMO DAY'),
('Big Data Community meetup'),
('Software Craftsmanship Meetup'),
('DevGAMM Spring 2021'),
('DUMP-2021'),
('HighLoad++');

-- fill status
insert into status (name)
values ('Applied'),
('Rejected'),
('Undecided');

-- fill requests
insert into requests (USER_ID, MEET_ID, STATUS_ID)
values (1, 1, 1),
(1, 2, 2),
(1, 4, 3),
(2, 1, 2),
(2, 3, 2),
(2, 5, 1),
(2, 6, 3),
(3, 6, 1),
(3, 5, 1),
(3, 4, 1),
(4, 2, 1),
(5, 3, 2),
(5, 4, 3),
(6, 6, 1),
(6, 5, 1),
(6, 2, 2),
(6, 1, 1),
(1, 6, 3),
(1, 5, 2),
(4, 1, 3),
(4, 5, 2),
(6, 3, 2);

--select meets with count by applied users
select m.name meet_name, count(user_id) applied_number
from requests r,
meetings m,
status s
where lower(s.name) = 'applied'
and meet_id = m.id
and status_id = s.id
group by m.name, s.name;

--select meets that no one requested
select distinct m.name meet_name
from requests, meetings m
where m.id not in (
select meet_id from requests
);
