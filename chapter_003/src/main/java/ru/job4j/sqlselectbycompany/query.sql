/* Author: fourbarman */

/*
1. В одном запросе получить
- имена всех person, которые не состоят в компании с id = 5;
- название компании для каждого человека.
2. Необходимо выбрать название компании с максимальным количеством человек + количество человек в этой компании.
*/

/*
Получить имена всех person, которые не состоят в компании с id = 5 и название компании для каждого человека.
 */

select person.name, company.name
from person,
     company
where person.company_id <> '5'
  and person.company_id = company.id;

/*
Выбрать название компании с максимальным количеством человек + количество человек в этой компании.
 */

select *
from (select company.name, count(person.name) as expr1
      from company,
           person
      where company.id = person.company_id
      group by company.name) as t1
order by expr1 desc
limit 1;