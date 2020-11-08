/* Author: fourbarman */

/*
 * 1. Create tables in items_storage.
 * 2. fill database with some starting information.
 */

create table rules
(
    id   serial primary key,
    name varchar(2000)
);

create table role
(
    id   serial primary key,
    name varchar(2000)
);

create table role_rules
(
    id       serial primary key,
    id_rules int references rules (id),
    id_role  int references role (id)
);

create table users
(
    id      serial primary key,
    id_role int references role (id),
    name    varchar(2000)
);

create table item_state
(
    id   serial primary key,
    name varchar(2000)
);

create table categories
(
    id   serial primary key,
    name varchar(2000)
);

create table items
(
    id            serial primary key,
    id_users      int references users (id),
    id_item_state int references item_state (id),
    id_categories int references categories (id),
    name          varchar(2000)
);

create table item_comments
(
    id      serial primary key,
    id_item int references items (id),
    name    varchar(2000)
);

create table attachments
(
    id      serial primary key,
    id_item int references items (id),
    name    varchar(2000)
);

insert into rules(name)
values ('просмотр');
insert into rules(name)
values ('редактирование');
insert into rules(name)
values ('удаление');

insert into role(name)
values ('администратор');
insert into role(name)
values ('пользователь');
insert into role(name)
values ('гость');

insert into role_rules(id_role, id_rules)
values (1, 1);
insert into role_rules(id_role, id_rules)
values (1, 2);
insert into role_rules(id_role, id_rules)
values (1, 3);
insert into role_rules(id_role, id_rules)
values (2, 1);
insert into role_rules(id_role, id_rules)
values (2, 2);
insert into role_rules(id_role, id_rules)
values (3, 1);

insert into users(id_role, name)
values (1, 'Анна');
insert into users(id_role, name)
values (2, 'Борис');
insert into users(id_role, name)
values (2, 'Владимир');
insert into users(id_role, name)
values (3, 'Глеб');
insert into users(id_role, name)
values (3, 'Дмитрий');
insert into users(id_role, name)
values (3, 'Евгений');

insert into categories(name)
values ('инцидент');
insert into categories(name)
values ('ошибка');
insert into categories(name)
values ('обслуживание');

insert into item_state(name)
values ('открыта');
insert into item_state(name)
values ('в работе');
insert into item_state(name)
values ('закрыта');

insert into items(id_users, id_item_state, id_categories, name)
values (1, 1, 1, 'Что то пошло не так');
insert into items(id_users, id_item_state, id_categories, name)
values (2, 2, 1, 'Все еще в работе');
insert into items(id_users, id_item_state, id_categories, name)
values (3, 2, 2, 'Это баг в работе');
insert into items(id_users, id_item_state, id_categories, name)
values (4, 3, 2, 'Закрытый баг');
insert into items(id_users, id_item_state, id_categories, name)
values (5, 2, 3, 'Надо обслужить что то');
insert into items(id_users, id_item_state, id_categories, name)
values (6, 3, 3, 'Уже обслужили');

insert into item_comments(id_item, name)
values (1, 'Коммент к первой заявке');
insert into item_comments(id_item, name)
values (3, 'Коммент к третьей заявке');
insert into item_comments(id_item, name)
values (4, 'Коммент к четвертой заявке');

insert into attachments(id_item, name)
values (1, 'Скриншот_ничего.jpg');
insert into attachments(id_item, name)
values (2, 'table.xml');
insert into attachments(id_item, name)
values (2, 'Скриншот.jpg');
insert into attachments(id_item, name)
values (5, 'postgre.db');
insert into attachments(id_item, name)
values (6, 'Скриншот.jpg');
insert into attachments(id_item, name)
values (6, 'msg.xml');
insert into attachments(id_item, name)
values (6, 'picture.pic');