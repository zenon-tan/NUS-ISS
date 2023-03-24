use northwind;

select * from customers;
select id, company, first_name, last_name, job_title, address, city, state_province 
from customers limit 5 offset 5;

select * from orders;
select id, customer_id, order_date, shipped_date, ship_address, ship_city, ship_state_province
from orders
where customer_id = 6;