use hotelreservation;

create table employee2(

	employee_id int not null auto_increment,
    name varchar(64) not null,
    salary int not null,
    primary key(employee_id)

);

select * from employee;
select * from employee where first_name = "Paris";

use northwind;

select * from customers;

select * from orders;

select * from orders where customer_id = 4;

select id, order_date, ship_address, ship_city, ship_country_name from orders
where customer_id = 4;

select distinct ship_name from orders where payment_type like "%credit%";

select payment_type, count(payment_type) as payment_type_count from orders 
group by payment_type
having count(payment_type) > 5
 order by count(payment_type) desc;
