/* Author: fourbarman */

/*
 Запрос получение всех продуктов с типом "СЫР"
 */
select p.name
from product p,
     type t
where p.type_id = t.id
  and t.name like 'СЫР';

/*
 Запрос получения всех продуктов, у кого в имени есть слово "мороженное"
 */
select name
from product
where name LIKE '%мороженное%';

/*
 Запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
 */
select product.name
from product
where (current_date + interval '1 month') > product.expired_date;

/*
 Запрос, который выводит самый дорогой продукт.
 */
select p.name
from product p
where p.price = (select MAX(price) from product);

/*
 Запрос, который выводит количество всех продуктов определенного типа.
 */
select t.name, COUNT(*) AS count_type_name
from product p
         join type t on p.type_id = t.id
group by t.name;

/*
 Запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
 */
select p.name
from product p,
     type t
where p.type_id = t.id
  and (t.name like '%МОЛОКО%' or t.name like '%СЫР%');

/*
 Запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
 */
select t.name, COUNT(*) AS count_type_name
from product p
         join type t on p.type_id = t.id
group by t.name
having COUNT(*) < 10;

/*
 Запрос все продукты и их тип
 */
select product.name, type.name
from product
         left join type
                   on product.type_id = type.id;