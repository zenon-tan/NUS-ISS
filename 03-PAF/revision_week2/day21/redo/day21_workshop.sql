use northwind;

select * from customers limit 5 offset 0;

select * from customers where id = 4;

select * from orders where customer_id = 4;