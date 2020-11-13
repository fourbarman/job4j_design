/* Author: fourbarman */

/*
В базе две сущности, представленные в таблицах departments и emploees.
Отношение one-to-many.
В таблицах только поле name.
*/

/*
Создать таблицы и заполнить их начальными данными
 */

create table departments(
id serial primary key,
name varchar(255)
);
--
create table emploees(
id serial primary key, name varchar(255),
id_dep int references departments(id)
);
--
insert into departments (name)
values
('Accounting Division'),
('Anesthesiology Department'),
('Architecture'),
('Art History'),
('Biological Sciences Department'),
('Chemistry'),
('Chemical Engineering Department')
;
--
insert into emploees (name, id_dep)
values
('John Weak', 1),
('Indiana Jones', 1),
('Han Solo', 1),
('John McClane', 3),
('James Bond', 4),
('Tyler Durden', 4),
('James Bond', 4),
('Darth Vader', 5),
('Michael Corleone', 6),
('Marty McFly', 7),
('Captain Jack Sparrow', 8),
('Ron Burgundy', 8),
('Rick Blaine', 9),
('Doc Brown', 10),
('Hannibal Lecter', 11),
('Forrest Gump', 11),
('Snake Plissken', 12),
('Peter Venkman', 13),
('Jules Winnfield', 14),
('Captain Mal Reynolds', 14);
--
insert into emploees (name)
values
('Ellen Ripley'),('The Dude'),('Jules Winnfield'),('Tommy DeVito');

/*
Используя left join найти работников, которые не относятся ни к одну из департаментов
 */
select * from emploees e left join departments d on e.id_dep = d.id where e.id_dep is null;

/*
Используя left и right join написать запросы, которые давали бы одинаковый результат.
 */
select * from emploees e left join departments d on e.id_dep = d.id;
select * from departments d right join emploees e on d.id = e.id_dep;

/*
Создать таблицу teens с атрибутами name, gender и заполнить ее.
Используя cross join составить все возможные разнополые пары
 */
create table teens (
	id serial primary key,
	name varchar(255),
	gender varchar(1)
);
--
insert into teens (name, gender) values
('Lou Bloom', 'M'),
('Amy Dunne', 'F'),
('Tommy DeVito', 'M'),
('Amélie Poulain', 'F'),
('Axel Foley', 'M'),
('Katniss Everdeen', 'M'),
('Jack Burton', 'M'),
('Sarah Connor', 'F'),
('Ace Ventura', 'M'),
('Marge Gunderson', 'F'),
('Hans Landa', 'M')
;
--
select * from (select name, gender from teens where gender = 'M') "Male" cross join (select name, gender from teens where gender = 'F') "Female";